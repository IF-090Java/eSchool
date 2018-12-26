package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.service.MarkTypeService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/mark_types")
@RequiredArgsConstructor
public class MarkTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);


    @NonNull
    private MarkTypeService markTypeService;


    /**
     * Teacher or admin get all mark type.
     * @return list of {@link MarkTypeDTO}
     *          as object in {@link GeneralResponseWrapper} with http status code.
     */
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @ApiOperation(value = "Teacher or admin gets all markTypes", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher is allowed to view all mark types"),
            @ExtensionProperty(name = "admin", value = "a admin is allowed to view all mark types")
    })})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping
    public GeneralResponseWrapper<List<MarkTypeDTO>> getAll() {
        LOGGER.info("Getting all mark types.");
        return new GeneralResponseWrapper<>(Status.of(OK), markTypeService.getAll());
    }

    /**
     * Teacher or admin get mark type by ID.
     * @param id ID of mark type.
     * @return {@link MarkTypeDTO}
     *          as object in {@link GeneralResponseWrapper} with http status code.
     */
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @ApiOperation(value = "Teacher or admin gets markType", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher is allowed to view mark type"),
            @ExtensionProperty(name = "admin", value = "a admin is allowed to view mark type")
    })})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/{id}")
    public GeneralResponseWrapper<MarkTypeDTO> getOne(@PathVariable("id") int id) {
        LOGGER.info("Getting mark type with id {}", id);
        return new GeneralResponseWrapper<>(Status.of(OK), markTypeService.getMarkTypeById(id));
    }

    /**
     * Admin add markType.
     * @param markTypeDTO data about mark type.
     * @return saved mark type
     *              as {@link MarkTypeDTO} object in {@link GeneralResponseWrapper} with http status code.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Admin add markType", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "a admin is allowed to add mark type")
    })})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    public GeneralResponseWrapper<MarkTypeDTO> addOne(@RequestBody MarkTypeDTO markTypeDTO) {
        LOGGER.info("Adding mark type [{} {}]", markTypeDTO.getMarkType(), markTypeDTO.getDescription());
        return new GeneralResponseWrapper<>(Status.of(CREATED), markTypeService.addMarkType(markTypeDTO));
    }

    /**
     * Admin update markType.
     * @param markTypeDTO data for update.
     * @param id ID of mark type.
     * @return updated mark type
     *              as {@link MarkTypeDTO} object in {@link GeneralResponseWrapper} with http status code.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Admin update markType", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "a admin is allowed to update mark type")
    })})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping("/{id}")
    public GeneralResponseWrapper<MarkTypeDTO> updateOne(@RequestBody MarkTypeDTO markTypeDTO, @PathVariable("id") int id) {
        LOGGER.info("Updating mark type with id {} to mark type [{} {}]", id, markTypeDTO.getMarkType(), markTypeDTO.getDescription());
        return new GeneralResponseWrapper<>(Status.of(OK), markTypeService.updateMarkType(id, markTypeDTO));
    }
}
