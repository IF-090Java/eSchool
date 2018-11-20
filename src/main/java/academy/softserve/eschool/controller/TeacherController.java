package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.repository.TeacherRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.TeacherService;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
@RequiredArgsConstructor
public class TeacherController {

    @NonNull
    private TeacherRepository teacherRepository;
    @NonNull
    private UserRepository userRepository;
    @NonNull
    private TeacherService teacherService;

    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public GeneralResponseWrapper<List<TeacherDTO>> getall() {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), teacherService.getAll(teacherRepository.findAll()));
    }

    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Teacher successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<TeacherDTO> addTeacher(
            @ApiParam(value = "teacher object", required = true) @RequestBody TeacherDTO teacher) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), teacherService.addOne(teacher));
    }

    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{idTeacher}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and principal.id == #idTeacher)")
    public GeneralResponseWrapper<TeacherDTO> getTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), teacherService.getOne(teacherRepository.findById(idTeacher).get()));
    }

    @PutMapping("/{idTeacher}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Teacher successfully updated"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER') and principal.id == #idTeacher")
    public GeneralResponseWrapper<TeacherDTO> updateTeacher(
            @ApiParam(value = "user object", required = true) @RequestBody EditUserDTO teacher,
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), teacherService.updateTeacher(userRepository.findById(idTeacher).get(), teacher));
    }
}

