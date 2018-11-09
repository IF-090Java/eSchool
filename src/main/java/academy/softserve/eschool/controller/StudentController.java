package academy.softserve.eschool.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@Api(description = "Student controller")
@RequiredArgsConstructor
public class StudentController {

    @NonNull
    private StudentRepository studentRepository;
    @NonNull
    StudentService studentService;

    @ApiOperation(value = "create new student, first name, last name and class passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Student addStudent(
            @ApiParam(value = "student object", required = true) @RequestBody StudentDTO student) {
        return studentService.addOne(student);
    }

    @GetMapping("/{idStudent}")
    @ApiOperation(value = "get student")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHER')")
    public StudentDTO getStudent(
            @ApiParam(value = "id of lesson", required = true) @PathVariable int idStudent) {
        return studentService.getOne(studentRepository.findById(idStudent).get());
    }

    @ApiOperation(value = "get students from class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/classes/{idClass}")
    public List<StudentDTO> getStudentsByClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass){
        return studentService.getAll(studentRepository.findByClazzId(idClass));
    }

    @PutMapping("/{idStudent}")
    @ApiOperation(value = "update profile of student")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Student successfully updated"),
                    @ApiResponse( code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasAnyRole('USER')")
    public void updateStudent(
            @ApiParam(value = "user object", required = true)  @RequestBody EditUserDTO student,
            @ApiParam(value = "id of student", required = true)  @PathVariable int idStudent){

        studentService.updateStudent(studentRepository.findById(idStudent).get(),student, "USER");

    }

}
