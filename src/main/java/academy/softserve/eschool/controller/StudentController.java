package academy.softserve.eschool.controller;

import academy.softserve.eschool.DTOs.StudentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Api(description = "Student controller")
public class StudentController {

    @PostMapping
    @ApiOperation(value = "Add student, first name, last name and class passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public void addStudent(StudentDTO student) {

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get some student info by id passed in url")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public StudentDTO getTeacher(@PathVariable int id){
        return new StudentDTO("John", "Doe", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx");
    }
}
