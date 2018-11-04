package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/transition")
@Api(value = "transition", description = "Endpoints for transition to new school year")
public class NYTransitionController {
    @Autowired ClassServiceImpl classService;
    @Autowired StudentService studentService;

    @PostMapping
    @ApiOperation(value = "Add new classes based on currently classes with new year and name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Boolean addNewYearClasses(){
        classService.addNewYearClasses();
        return true;
    }

    @PutMapping
    @ApiOperation(value = "Binding students to new classes, deactivate previous year classes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public List<NYTransitionDTO> bindingStudentsToNewClasses(
            @ApiParam(value = "transition class(new and old classes id)", required = true) @RequestBody List<NYTransitionDTO> transitionDTOS){
        classService.updateClassStatusById(transitionDTOS, false);
        studentService.studentClassesRebinding(transitionDTOS);
        return transitionDTOS;
    }
}