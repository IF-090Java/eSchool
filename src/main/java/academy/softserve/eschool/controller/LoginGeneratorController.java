package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.service.LoginGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//todo bk extract custom translit logic into service.
//todo bk ++ guys how long should i remind you to put javadocs
@RestController("/login")
@Api(description = "Login generator controller")
public class LoginGeneratorController {
    @Autowired
    LoginGeneratorService loginGeneratorService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successfully generated"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create login")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate")
    public DataForLoginDTO getLogin(@RequestBody DataForLoginDTO data) {
        return loginGeneratorService.generateLogin(data);
    }
}