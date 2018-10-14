package academy.softserve.eschool.controller;

import academy.softserve.eschool.DTOs.StudentDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping
    public void addStudent(StudentDTO student) {

    }

    @GetMapping("/{id}/profile")
    @ApiOperation(value = "Get info for teacher profail")
    public StudentDTO getTeacher(@PathVariable int id){
        return new StudentDTO("first", "last", "6-a");
    }
}
