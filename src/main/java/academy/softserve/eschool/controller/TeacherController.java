package academy.softserve.eschool.controller;


import academy.softserve.eschool.DTOs.AddTeacherDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PostMapping
    public void addTeacher(AddTeacherDTO teacher) {
    }


    @GetMapping("/{id}")
    public AddTeacherDTO getTeacher(@PathVariable int id){
        return new AddTeacherDTO("first", "last", "math", "6-a");
    }

}
