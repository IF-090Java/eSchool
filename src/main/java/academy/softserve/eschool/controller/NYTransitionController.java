package academy.softserve.eschool.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students/transition")
@Api(value = "transition", description = "Endpoints for transition to new school year")
@RequiredArgsConstructor
public class NYTransitionController {

    //todo bk ++ use autowiring via constructors.
	@NonNull
    ClassServiceImpl classService;
	@NonNull
    StudentService studentService;

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