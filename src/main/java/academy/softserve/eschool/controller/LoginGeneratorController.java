package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.service.LoginGeneratorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
@Api(description = "Operation to create login")
public class LoginGeneratorController {
    @Autowired
    LoginGeneratorService loginGeneratorService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successfully generated"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Admin creates a login", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to create a login")})})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate")
    public DataForLoginDTO getLogin(@ApiParam(value = "Data for login", required = true) @RequestBody DataForLoginDTO data) {
        return loginGeneratorService.generateLogin(data);
    }
}