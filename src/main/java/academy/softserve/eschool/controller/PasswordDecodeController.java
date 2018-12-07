package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.AddedUsersDTO;
import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.PasswordDecodeService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * The controller {@code PasswordDecodeController} contains methods, that are
 * mapped to special URL patterns (API Endpoints) for admin to get users and their decoded passwords.
 * Methods return raw data back to the client in JSON representations.
 */
@RestController
@RequestMapping("/users/credentials")
@Api(value = "Password decode encoder", description = "Created for admin to get users and their decoded passwords")
@RequiredArgsConstructor
public class PasswordDecodeController {
    @NonNull
    private PasswordDecodeService passwordDecodeService;

    /**
     * Add classes to next year based on currently
     *
     * @return List of created {@link ClassDTO} objects
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Admin get users and their decoded passwords", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to users and their decoded passwords")})})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public GeneralResponseWrapper<List<AddedUsersDTO>> getAll(){
        return new GeneralResponseWrapper<>(Status.of(OK), passwordDecodeService.decodemultiple());
    }


}
