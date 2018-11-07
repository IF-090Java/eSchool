package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/transition")
@Api(value = "transition", description = "Endpoints for transition to new school year")
public class NYTransitionController {

    //todo bk ++ use autowiring via constructors.
    @Autowired ClassServiceImpl classService;
    @Autowired StudentService studentService;

    @ApiOperation(value = "Add new classes based on currently classes with new year and name")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public List<ClassDTO> addNewYearClasses(){
        return classService.addNewYearClasses();
    }

    @PutMapping
    @ApiOperation(value = "Binding students to new classes, deactivate previous year classes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public List<NYTransitionDTO> bindingStudentsToNewClasses(
            @ApiParam(value = "transition class(new and old classes id)", required = true) @RequestBody List<NYTransitionDTO> transitionDTOS){
        classService.updateClassStatusById(transitionDTOS, false);
        studentService.studentClassesRebinding(transitionDTOS);
        return transitionDTOS;
    }
}