package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.service.StudentService;
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

@RestController
@RequestMapping("/students")
@Api(value = "Pupil's Endpoints", description = "Operations with pupils")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @NonNull
    private StudentRepository studentRepository;

    @NonNull
    StudentService studentService;

    /**
     * Admin creates a new pupil.
     * First name, last name, patronymic, date of birth and class in {@link StudentDTO} are required.
     * @param student User object.
     * @return Saved pupil as {@link TeacherDTO} object
     *          in {@link GeneralResponseWrapper} with http status code.
     */
    @ApiOperation(value = "Admin creates a new student (first name, last name and class passed in html)", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to create a new student")})})
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GeneralResponseWrapper<Student> addStudent(
            @ApiParam(value = "Student object", required = true) @RequestBody StudentDTO student) {
        LOGGER.info("Creating student [{} {}]", student.getLastname(), student.getFirstname());
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.CREATED), studentService.addOne(student));
    }

    /**
     * Admin, teacher or pupil get all info about pupil by ID of pupil.
     * The admin or teacher is allowed to get all info about a pupil.
     * A pupil is allowed to get all info about himself.
     * @param idStudent ID of pupil.
     * @return Pupil data in {@link StudentDTO} object
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @GetMapping("/{idStudent}")
    @ApiOperation(value = "User gets pupil", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to view the information about the pupil"),
            @ExtensionProperty(name = "teacher", value = "a teacher is allowed to view the information about the pupil"),
            @ExtensionProperty(name = "user", value = "a pupil is allowed to see information about himself")})})
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER') or (hasRole('USER') and principal.id == #idStudent)")
    public GeneralResponseWrapper<StudentDTO>getStudent(
            @ApiParam(value = "ID of student", required = true) @PathVariable int idStudent) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), studentService.getOne(studentRepository.findById(idStudent).get()));
    }

    /**
     * Admin or teacher get all info about pupil in class with passed ID.
     * The admin or teacher is allowed to get all all pupils.
     * @param idClass ID of class.
     * @return List of {@link StudentDTO} object
     *         in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Admin or teacher gets active pupils from class", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher is allowed to view information about the pupils of a class"),
            @ExtensionProperty(name = "admin", value = "the admin is allowed to view information about the pupils of a class")})})
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/classes/{idClass}")
    public GeneralResponseWrapper<List<StudentDTO>> getStudentsByClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), studentService.getAll(studentRepository.findByClazzId(idClass)));
    }

    /**
     * Pupil updates his profile.
     * Only a pupil is allowed to update his profile.
     * @param student User object
     * @param idStudent ID of the pupil.
     * @return Updated teacher as {@link StudentDTO} object
     *          in {@link GeneralResponseWrapper} with http status code.
     */
    @PutMapping("/{idStudent}")
    @ApiOperation(value = "User updates the profile of the pupil", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "user", value = "a pupil is allowed to update his own profile")})})
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Student successfully updated"),
                    @ApiResponse( code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('USER') and principal.id == #idStudent")
    public GeneralResponseWrapper<User> updateStudent(
            @ApiParam(value = "User object", required = true)  @RequestBody EditUserDTO student,
            @ApiParam(value = "ID of pupil", required = true)  @PathVariable int idStudent){
        LOGGER.info("Updating student [{} {}]", student.getLastname(), student.getFirstname());

        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK) , studentService.updateStudent(studentRepository.findById(idStudent).get(), student));
    }

}
