package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
@Api(description = "Student controller")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    private static List<StudentDTO> list = new ArrayList<>();


    @PostMapping
    @ApiOperation(value = "Add student, first name, last name and class passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public Student addStudent(@RequestBody StudentDTO student) {
        return studentService.addOne(student);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get student")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public StudentDTO getStudent(@PathVariable int id) {
        return studentService.getOne(studentRepository.findById(id).get());
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
        return studentService.getAll(studentRepository.findByClazzId(id));
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
    public void updateStudent(@RequestBody EditUserDTO student, @PathVariable int id){

        studentService.updateStudent(studentRepository.findById(id).get(),student);

    }

}
