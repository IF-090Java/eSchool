package academy.softserve.eschool.controller;

import academy.softserve.eschool.DTOs.TeacherDTO;
import academy.softserve.eschool.DTOs.TeacherNamesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
public class TeacherController {
    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public List<TeacherNamesDTO> getall(){
        List<TeacherNamesDTO> list = new ArrayList<>();
        list.add(new TeacherNamesDTO(1,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(2,"Іван", "Петрович"));
        list.add(new TeacherNamesDTO(3,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(4,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(5,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(6,"Іван","Петрович"));
        return list;
    }
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public TeacherDTO getTeacher(){
        return new TeacherDTO("Іван","Іванов","вавава","*******","vanya@mail","05050505056");
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
    public void updateTeacher(@RequestBody TeacherDTO teacher,@PathVariable String id){
        // someservice.update(id,TeacherDTO)
    }



}
