package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller {@code ClassController} contains methods, that
 * mapped to the special URL patterns (API Endpoints) for working with classes
 * and receive requests from {@link org.springframework.web.servlet.DispatcherServlet}.
 * Methods return raw data back to the client in JSON representations.
 *
 * @author Vitaliy Popovych
 */
@RestController
@RequestMapping("/classes")
@Api(description = "Endpoints for classes: add a class, get a class by ID, get all classes and edit them.")
@RequiredArgsConstructor
public class ClassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@NonNull
    private ClassServiceImpl classService;

    /**
     * Create new class
     *
     * @param newClassDTO New class object
     * @return Created class as {@link ClassDTO} object in {@link GeneralResponseWrapper}
     *         with http status code
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Class successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Admin creates a class")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GeneralResponseWrapper<ClassDTO> addClass(
            @ApiParam(value = "Class object", required = true) @RequestBody ClassDTO newClassDTO){
        LOGGER.info("An attempt to add class " +newClassDTO.getClassName() +" "+newClassDTO.getClassYear());
        return new GeneralResponseWrapper<>(
                Status.of(HttpStatus.CREATED),
                classService.saveClass(newClassDTO));
    }

    /**
     * Returns class as {@link ClassDTO} object by class ID
     *
     * @param classId Id of class
     * @return Class as {@link ClassDTO} object in {@link GeneralResponseWrapper}
     *         with http status code
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "User gets a class by ID")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and @securityExpressionService.teachesInClass(principal.id, #classId)) " +
            "or (hasRole('USER') and @securityExpressionService.isMemberOfClass(principal.id, #classId))")
    @GetMapping("/{classId}")
    public GeneralResponseWrapper<ClassDTO> getClassById(
            @ApiParam(value = "ID of class", required = true) @PathVariable int classId){
        return new GeneralResponseWrapper<>(
                Status.of(HttpStatus.OK),
                classService.findClassById(classId)
        );
    }

    /**
     * Returns list of {@link ClassDTO} objects with all classes.
     * If {@code subject_id} request parameter set, returns list of {@link ClassDTO} objects
     * with classes that study subject with specified id
     *
     * @param subjectId Id od subject
     * @return List of {@link ClassDTO} objects with all classes,
     *         or with classes that study subject with specified id
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Admin or teacher gets all classes")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping
    public GeneralResponseWrapper<List<ClassDTO>> getAllClasses(
            @ApiParam(value="Only the classes that learn the subject with specified ID will be returned") @RequestParam(required=false) Integer subjectId){
        if (subjectId == null) {
            return new GeneralResponseWrapper<>(
                    Status.of(HttpStatus.OK),
                    classService.getAllClasses()
            );
        } else {
            return new GeneralResponseWrapper<>(
                    Status.of(HttpStatus.OK),
                    classService.getClassesBySubject(subjectId)
            );
        }
    }

    /**
     * Update some class
     *
     * @param id Id of class
     * @param editableClass {@link ClassDTO} object of class that need to be updated
     * @return Updated class as {@link ClassDTO} object
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Class successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Admin updates a class with specified ID")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public GeneralResponseWrapper<ClassDTO> editClass(
            @ApiParam(value = "ID of class", required = true) @PathVariable int id,
            @ApiParam(value = "Class object", required = true) @RequestBody ClassDTO editableClass){

        LOGGER.info("An attempt to update class with id " +id);
        return new GeneralResponseWrapper<>(
                Status.of(HttpStatus.CREATED),
                classService.updateClass(id, editableClass)
        );
    }
}