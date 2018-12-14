package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.service.MarkTypeService;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mark_types")
@RequiredArgsConstructor
public class MarkTypeController {
    @NonNull
    private MarkTypeService markTypeService;


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
    public List<MarkTypeDTO> getAll() {
        return markTypeService.getAll();
    }

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
    public MarkTypeDTO getOne(@PathVariable("id") int id) {
        return markTypeService.getMarkTypeById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    public MarkTypeDTO addOne(@RequestBody MarkTypeDTO markTypeDTO) {
        return markTypeService.addMarkType(markTypeDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping("/{id}")
    public MarkTypeDTO updateOne(@RequestBody MarkTypeDTO markTypeDTO, @PathVariable("id") int id) {
        return markTypeService.updateMarkType(id, markTypeDTO);
    }
}
