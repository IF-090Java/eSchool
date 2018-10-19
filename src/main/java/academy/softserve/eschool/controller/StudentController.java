package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.StudentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public StudentDTO addStudent(@RequestBody StudentDTO student) {
        return student;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get some student info by id passed in url")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public StudentDTO getStudent(@PathVariable int id){
        return new StudentDTO(1,"John", "Doe", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update profile of student")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateStudent(@RequestBody StudentDTO student, @PathVariable String id){
        // someservice.update(id,student)
    }
    @GetMapping("/classes/{id}")
    @ApiOperation(value = "get students from class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public List<StudentDTO> getStudentsByClass(@PathVariable int id){
        List<StudentDTO> list = new ArrayList<>();
        list.add(new StudentDTO(1, "Cемків", "Василь", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(2, "Романчук", "Віктор", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(3, "Кривенчук", "Ігор", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(4, "Приймак", "Вікторія", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(5, "Семенів", "Ольга", "7-b", "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        return list;
    }
}
