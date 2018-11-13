package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.StudentService;
import academy.softserve.eschool.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(description = "The administrator changes user data")
@RequiredArgsConstructor
public class AdminEditUserController {

    @NonNull
    private StudentRepository studentRepository;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private StudentService studentService;

    @NonNull
    private TeacherService teacherService;

    @ApiOperation(value = "Admin update profile of student")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/students/{idStudent}")
    public void updateStudent(@RequestBody EditUserDTO student, @PathVariable int idStudent){

        studentService.updateStudent(studentRepository.findById(idStudent).get(),student, "ADMIN");

    }

    @ApiOperation(value = "Admin update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/teachers/{idTeacher}")
    public void updateTeacher(@RequestBody EditUserDTO teacher, @PathVariable int idTeacher){
        //todo bk ++ why did you hardcoded "ADMIN" here. Avoid this.
        teacherService.updateTeacher(userRepository.findById(idTeacher).get(),teacher, "ADMIN");
    }
}
