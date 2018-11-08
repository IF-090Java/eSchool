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
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.TeacherRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
@RequiredArgsConstructor
public class TeacherController {

    @NonNull
    private TeacherRepository teacherRepository;
    @NonNull
    private UserRepository userRepository;
    @NonNull
    private TeacherService teacherService;

    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public List<TeacherDTO> getall(){
        return teacherService.getAll(teacherRepository.findAll());
    }
  
    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Teacher successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher addTeacher(
            @ApiParam(value = "teacher object", required = true) @RequestBody TeacherDTO teacher) {
        return teacherService.addOne(teacher);
    }
  
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{idTeacher}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public TeacherDTO getTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        return teacherService.getOne(teacherRepository.findById(idTeacher).get());
    }

    @PutMapping("/{idTeacher}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Teacher successfully updated"),
                    @ApiResponse( code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasAnyRole('TEACHER')")
    public void updateTeacher(
            @ApiParam(value = "user object", required = true) @RequestBody EditUserDTO teacher,
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){

        teacherService.updateTeacher(userRepository.findById(idTeacher).get(),teacher, "TEACHER");
    }



}

