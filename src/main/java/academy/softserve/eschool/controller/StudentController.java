package academy.softserve.eschool.controller;

import academy.softserve.eschool.DTOs.AddStudentDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping
    public void addStudent(AddStudentDTO student) {

    }

    @GetMapping("/{id}")
    public AddStudentDTO getTeacher(@PathVariable int id){
        return new AddStudentDTO("first", "last", "6-a");
    }
}
