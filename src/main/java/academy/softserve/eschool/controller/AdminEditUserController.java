package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.StudentService;
import academy.softserve.eschool.service.TeacherService;
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

@RestController
@RequestMapping("/admin")
@Api(description = "Operations with changing user data for the admin.")
@RequiredArgsConstructor
public class AdminEditUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @NonNull
    private StudentRepository studentRepository;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private StudentService studentService;

    @NonNull
    private TeacherService teacherService;

    /**
     * Admin updates the profile of the pupil by passing {@linc EditUserDTO} and ID of the pupil.
     * Only admin has access to this endpoint.
     * The admin is allowed to update every account.
     * @param student User object
     * @param idStudent ID of the pupil.
     * @return Updated student as {@link User} object
     *          in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Admin updates the profile of the pupil", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to update every account")})})
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/students/{idStudent}")
    public GeneralResponseWrapper<User> updateStudent(
            @ApiParam(value = "User object.", required = true) @RequestBody EditUserDTO student,
            @ApiParam(value = "ID of the pupil.", required = true) @PathVariable int idStudent){
        LOGGER.info("Updating student [{} {}]", student.getLastname(), student.getFirstname());
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK) , studentService.adminUpdateStudent(studentRepository.findById(idStudent).get(), student));

    }

    /**
     * Admin updates the profile of the teacher by passing {@linc EditUserDTO} and ID of the teacher.
     * Only admin has access to this endpoint.
     * The admin is allowed to update every account.
     * @param teacher User object
     * @param idTeacher ID of the teacher.
     * @return Updated teacher as {@link TeacherDTO} object
     *          in {@link GeneralResponseWrapper} with http status code.
     */
    @ApiOperation(value = "Admin updates the profile of the teacher.", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to update every account")})})
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/teachers/{idTeacher}")
    public GeneralResponseWrapper<TeacherDTO> updateTeacher(
            @ApiParam(value = "User object.", required = true) @RequestBody EditUserDTO teacher,
            @ApiParam(value = "ID of the teacher.", required = true) @PathVariable int idTeacher){
        LOGGER.info("Updating teacher [{} {}]", teacher.getLastname(), teacher.getFirstname() + "created");
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), teacherService.adminUpdateTeacher(userRepository.findById(idTeacher).get(), teacher));
    }
}
