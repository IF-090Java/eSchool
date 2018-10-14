package academy.softserve.eschool.controller;


import academy.softserve.eschool.DTOs.TeacherDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PostMapping
    public void addTeacher(TeacherDTO teacher) {
    }


    @GetMapping("/{id}/profile")
    public TeacherDTO getTeacher(@PathVariable int id){
        return new TeacherDTO("first", "last", "math", Arrays.asList(new String[]{"6-a", "7-a"}));
    }

}
