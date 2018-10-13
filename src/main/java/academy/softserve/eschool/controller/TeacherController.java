package academy.softserve.eschool.controller;


import academy.softserve.eschool.DTOs.AddTeacherDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PostMapping
    public void addTeacher(@RequestBody AddTeacherDTO teacher) {

    }
}
