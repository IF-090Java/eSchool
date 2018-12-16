package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.AddedUsersDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.PasswordDecodeService;
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

    @GetMapping("")
    @ApiOperation(value = "Admin gets the list of all users (with passwords)", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to get the list of all users")})})
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<List<AddedUsersDTO>> getAllUsers(){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), passwordDecodeService.decodemultiple(userRepository.getRegisteredUsers()));
    }

    @ApiOperation(value = "Deactivating account by id", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to deactivate accounts")})})
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{idUser}")
    public ResponseEntity<Object> deactivate(@ApiParam(value = "ID of user", required = true) @PathVariable int idUser){
        Optional<User> optionalUser = userRepository.findById(idUser);
        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }else {
            throw new UsernameNotFoundException("No user with given id");
        }
        user.setEnabled(false);
        userRepository.save(user);
        return ResponseEntity.ok(null);
    }
}
