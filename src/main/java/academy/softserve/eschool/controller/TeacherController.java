package academy.softserve.eschool.controller;


import academy.softserve.eschool.DTOs.TeacherDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "teacher crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public void addTeacher(TeacherDTO teacher) {
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "get some teacher info by id passed in url")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "teacher found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public TeacherDTO getTeacher(@PathVariable int id){
        return new TeacherDTO("Ivan", "Popovych", "teacher", "email", "099xxxxxxx");
    }

}
