package academy.softserve.eschool.controller;

import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.TeacherService;
import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.repository.TeacherRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public List<TeacherDTO> getall(){
        return teacherService.getAll(teacherRepository.findAll());
    }
  
    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Teacher successfully created"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Teacher addTeacher(
            @ApiParam(value = "teacher object", required = true) @RequestBody TeacherDTO teacher) {
        return teacherService.addOne(teacher);
    }
  
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{idTeacher}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public TeacherDTO getTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        return teacherService.getOne(teacherRepository.findById(idTeacher).get());
    }

    @PutMapping("/{idTeacher}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Teacher successfully updated"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateTeacher(
            @ApiParam(value = "user object", required = true) @RequestBody EditUserDTO teacher,
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){

        teacherService.updateTeacher(userRepository.findById(idTeacher).get(),teacher);
    }



}

