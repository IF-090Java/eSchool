package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.LoginDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping
    public String login(@RequestBody LoginDTO loginDTO){
        if (loginDTO.getLogin().equalsIgnoreCase("teacher")) return "{\"url\" : \"/teachers/21/profile\"}";
        else if (loginDTO.getLogin().equalsIgnoreCase("student")) return "{\"url\" : \"/students/131/profile\"}";
        else if (loginDTO.getLogin().equalsIgnoreCase("parents")) return "{\"url\" : \"/students/131/profile\"}";
        else if (loginDTO.getLogin().equalsIgnoreCase("admin")) return "{\"url\" : \"/admin\"}";
        else return "{\"url\" : \"/\"}";
    }
}
