package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.security.service.MethodSecurityExpressionService;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@Api(value = "classes", description = "Endpoints for classes")
@RequiredArgsConstructor
public class ClassController {
	@NonNull
    private ClassServiceImpl classService;

	@NonNull
    private MethodSecurityExpressionService methodSecurityService;

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
    @ApiOperation(value = "Create class")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GeneralResponseWrapper<ClassDTO> addClass(
            @ApiParam(value = "class object", required = true) @RequestBody ClassDTO newClassDTO){
        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_CREATED, "New class created"),
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
    @ApiOperation(value = "Get Class")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER') or (hasRole('USER') and @classController.isMemberOfClass(principal.id, #classId))")//for teacher needs access to the statistics page for all classes
    @GetMapping("/{classId}")
    public GeneralResponseWrapper<ClassDTO> getClassById(
            @ApiParam(value = "id of class", required = true) @PathVariable int classId){
        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_OK, "OK"),
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
    @ApiOperation(value = "Get all classes")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping
    public GeneralResponseWrapper<List<ClassDTO>> getAllClasses(
            @ApiParam(value="Only classes that study subject with specified id will be returned") @RequestParam(required=false) Integer subjectId){
        if (subjectId == null) {
            return new GeneralResponseWrapper<>(
                    new Status(HttpServletResponse.SC_OK, "OK"),
                    classService.getAllClasses()
            );
        } else {
            return new GeneralResponseWrapper<>(
                    new Status(HttpServletResponse.SC_OK, "OK"),
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
    @ApiOperation("Update class")
    @PreAuthorize("hasRole('ADMIN')")
    //todo bk ++ it's better to name param as classId instead. But I'd name it as 'id' in current case.
    //todo bk It's too obvious that 'classId' belongs to the class. But in case you have few ids then name it properly
    @PutMapping("/{id}")
    public GeneralResponseWrapper<ClassDTO> editClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int id,
            @ApiParam(value = "object of class", required = true) @RequestBody ClassDTO editableClass){
        //todo bk ++ updating objects with native queries bring a lot af mess into the app. And it's hard to support them. Use entity and repository for it.

        return new GeneralResponseWrapper<>(
                new Status(HttpServletResponse.SC_CREATED, "Class successfully updated"),
                classService.updateClass(id, editableClass)
        );
    }

    public boolean isMemberOfClass(int studentId, int classId){
        return methodSecurityService.isMemberOfClass(studentId, classId);
    }
}