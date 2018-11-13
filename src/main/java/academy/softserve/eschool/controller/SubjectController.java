package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.service.SubjectServiceImpl;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subjects")
@Api(value = "subjects", description = "API endpoints for subjects")
@RequiredArgsConstructor
public class SubjectController {
    @NonNull
    private SubjectServiceImpl subjectServiceImpl;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
            })
    @ApiOperation(value = "Get all subjects")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")//need access for teacher on statistics page
    @GetMapping()
    public List<SubjectDTO> getAll(
            @ApiParam("only subjects studied in specified class will be returned") @RequestParam(required=false) Integer classId) {
        if (classId == null) {
            return subjectServiceImpl.getAll();
        } else {
            return subjectServiceImpl.getSubjectsByClass(classId);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get a subject by Id")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/{idSubject}")
    public SubjectDTO getSubjectById(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject) {
        return subjectServiceImpl.getSubjectById(idSubject);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get all subjects by teacher", response = SubjectDTO.class)
    @PreAuthorize("hasRole('TEACHER') and principal.id == #idTeacher")
    @GetMapping("/teachers/{idTeacher}")
    public List<SubjectDTO> getSubjectsTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher) {
        return subjectServiceImpl.getSubjectsByTeacher(idTeacher);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subject successfully created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Add new subject")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public SubjectDTO addSubject(
            @ApiParam(value = "subject object", required = true) @RequestBody SubjectDTO newSubject) {
         subjectServiceImpl.addSubject(newSubject);
        return newSubject;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subject successfully updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Edit a subject")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idSubject}")
    public SubjectDTO editSubject(
            @ApiParam(value = "id of object", required = true) @PathVariable int idSubject,
            @ApiParam(value = "subject object", required = true) @RequestBody SubjectDTO editSubject) {
            subjectServiceImpl.editSubject(idSubject, editSubject);
        return editSubject;
    }
}
