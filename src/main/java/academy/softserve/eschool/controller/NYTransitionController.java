package academy.softserve.eschool.controller;

import academy.softserve.eschool.service.ClassService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.service.StudentService;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
@Api(value = "Transition Endpoints", description = "Operations for transition to new school year")
@RequiredArgsConstructor
public class NYTransitionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NYTransitionController.class);

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
    @ApiOperation(value = "Admin adds new classes based on currently classes with new year and name", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to add new classes based on currently classes with new year and name")})})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public GeneralResponseWrapper<List<ClassDTO>> addNewYearClasses(){
        LOGGER.info("An attempt to add classes for new academic year");
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
    @ApiOperation(value = "Admin binds students to new classes, deactivate previous year classes", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to bind students to new classes, deactivate previous year classes")})})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<List<NYTransitionDTO>> bindingStudentsToNewClasses(
            @ApiParam(value = "Transition of the class(new and old classes ID)", required = true) @RequestBody List<NYTransitionDTO> transitionDTOS){
        LOGGER.info("An attempt to update old year class status to false and rebind students to new classes.");
        classService.updateClassStatusById(transitionDTOS, false);
        studentService.studentClassesRebinding(transitionDTOS);
        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_CREATED, "Old classes disabled, students bindet to new classes"),
                transitionDTOS
        );
    }
}