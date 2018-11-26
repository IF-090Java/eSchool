package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.service.StudentService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Api(value = "Student's Endpoints", description = "Student controller")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @NonNull
    private StudentRepository studentRepository;
    @NonNull
    StudentService studentService;

    @ApiOperation(value = "Admin creates a new student (first name, last name and class passed in html)")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GeneralResponseWrapper<Student> addStudent(
            @ApiParam(value = "Student object", required = true) @RequestBody StudentDTO student) {
        logger.info("Student " + student.getLastname() + " " + student.getFirstname() + " created");
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.CREATED), studentService.addOne(student));
    }

    @GetMapping("/{idStudent}")
    @ApiOperation(value = "User gets student")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER') or (hasRole('USER') and principal.id == #idStudent)")
    public GeneralResponseWrapper<StudentDTO>getStudent(
            @ApiParam(value = "ID of lesson", required = true) @PathVariable int idStudent) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), studentService.getOne(studentRepository.findById(idStudent).get()));
    }

    @ApiOperation(value = "Admin or teacher get students from class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/classes/{idClass}")
    public GeneralResponseWrapper<List<StudentDTO>> getStudentsByClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), studentService.getAll(studentRepository.findByClazzId(idClass)));
    }

    @PutMapping("/{idStudent}")
    @ApiOperation(value = "User updates the profile of the pupil")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Student successfully updated"),
                    @ApiResponse( code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('USER') and principal.id == #idStudent")
    public GeneralResponseWrapper<User> updateStudent(
            @ApiParam(value = "User object", required = true)  @RequestBody EditUserDTO student,
            @ApiParam(value = "ID of pupil", required = true)  @PathVariable int idStudent){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK) , studentService.updateStudent(studentRepository.findById(idStudent).get(), student));

    }

}
