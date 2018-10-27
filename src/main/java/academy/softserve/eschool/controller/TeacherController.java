package academy.softserve.eschool.controller;

import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.TeacherService;
import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public List<TeacherDTO> getall(){
        return teacherService.getAll(teacherRepository.findAll());
    }
  
    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "teacher crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public Teacher addTeacher(@RequestBody TeacherDTO teacher) {
        return teacherService.addOne(teacher);
    }
  
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public TeacherDTO getTeacher(@PathVariable int id){
        return teacherService.getOne(teacherRepository.findById(id).get());
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateTeacher(@RequestBody EditUserDTO teacher, @PathVariable int id){

        teacherService.updateTeacher(userRepository.findById(id).get(),teacher);
    }



}

