package academy.softserve.eschool.controller;


import academy.softserve.eschool.DTOs.AddTeacherDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PostMapping
    public void addTeacher(AddTeacherDTO teacher) {
        System.out.println("teacher = [" + teacher + "]");

    }

    @GetMapping
    public String getTeachers(){
        return "In getTeachers()";
    }

}
