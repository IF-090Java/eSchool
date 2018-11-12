package academy.softserve.eschool.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import academy.softserve.eschool.service.ClassService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.service.StudentService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The controller {@code NYTransitionController} contains methods, that
 * mapped to the special URL patterns (API Endpoints) for working with classes transition to new year
 * and receive requests from {@link org.springframework.web.servlet.DispatcherServlet}.
 * Methods return raw data back to the client in JSON representations.
 *
 * @author Vitaliy Popovych
 */
@RestController
@RequestMapping("/students/transition")
@Api(value = "transition", description = "Endpoints for transition to new school year")
@RequiredArgsConstructor
public class NYTransitionController {

    //todo bk ++ use autowiring via constructors.
    //todo bk you should not use Impl class here. Use interface for injection and make the fields private
    @NonNull
    private ClassService classService;
    @NonNull
    private StudentService studentService;

    /**
     * Add classes to next year based on currently
     *
     * @return List of created {@link ClassDTO} objects
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Add new classes based on currently classes with new year and name")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public GeneralResponseWrapper<List<ClassDTO>> addNewYearClasses(){
        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_CREATED, "New classes successfully added"),
                classService.addNewYearClasses()
        );
    }

    /**
     * Set previous year classes status isActive as false,
     * add all students from previous year classes to new year classes
     *
     * @param transitionDTOS {@link NYTransitionDTO} object
     * @return List of {@link NYTransitionDTO} objects
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @PutMapping
    @ApiOperation(value = "Binding students to new classes, deactivate previous year classes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<List<NYTransitionDTO>> bindingStudentsToNewClasses(
            @ApiParam(value = "transition class(new and old classes id)", required = true) @RequestBody List<NYTransitionDTO> transitionDTOS){
        classService.updateClassStatusById(transitionDTOS, false);
        studentService.studentClassesRebinding(transitionDTOS);
        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_CREATED, "Old classes disabled, students bindet to new classes"),
                transitionDTOS
        );
    }
}