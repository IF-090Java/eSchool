package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.AddedUsersDTO;
import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.EmailService;
import academy.softserve.eschool.service.PasswordDecodeService;
import academy.softserve.eschool.service.UserService;
import academy.softserve.eschool.service.base.CredentialsMailingServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(description = "Operations with users entity")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserRepository userRepository;
    @NonNull
    private PasswordDecodeService passwordDecodeService;

    @NonNull
    private UserService userService;
    @NonNull
    private CredentialsMailingServiceBase credentialsMailingService ;

    @GetMapping("/credentials/teachers")
    @ApiOperation(value = "Admin gets the list of all teachers (with passwords) on his email", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to get the list of all teachers")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<Void> emailTeachersCredentials(){
        credentialsMailingService.sendTeachersCredentials();
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), null);
    }
    
    @GetMapping("/credentials/students")
    @ApiOperation(value = "Admin gets the list of all students (with passwords) on his email", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to get the list of all students")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<Void> emailStudentsCredentials(@RequestParam Integer classId){
        credentialsMailingService.sendStudentsCredentials(classId);
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), null);
    }

    @ApiOperation(value = "Deactivating account by id", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "admin is allowed to deactivate accounts")})})
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{idUser}")
    public ResponseEntity<EditUserDTO> deactivate(@ApiParam(value = "ID of user", required = true) @PathVariable int idUser){
        return ResponseEntity.ok(userService.deactivate(idUser));
    }

    @ApiOperation(value = "Check if mail is free", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "everybody", value = "Everyone is allowed to get info about  users")})})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'USER')")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User with given mail already exists"),
                    @ApiResponse(code = 404, message = "mail is free"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @RequestMapping(value = "/email/{mail}/", method = RequestMethod.HEAD)
    @ResponseBody
    public GeneralResponseWrapper<EditUserDTO> getByMail(@PathVariable String mail){
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), userService.getUserByEmail(mail));
    }

    @ApiOperation(value = "Check if login is free", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "admin is allowed to get info about  users")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User with given login already exists"),
                    @ApiResponse(code = 404, message = "login is free"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/login/{login}/", method = RequestMethod.HEAD)
    @ResponseBody
    public GeneralResponseWrapper<EditUserDTO> getByLogin(@PathVariable String login){
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), userService.getUserByLogin(login));
    }
}
