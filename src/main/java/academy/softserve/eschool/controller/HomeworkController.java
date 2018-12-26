package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
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
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Operations with getting homework")
@RequiredArgsConstructor
public class HomeworkController {

    @NonNull
    private JournalServiceImpl journalServiceImpl;

    /**
     * Returns list of {@link HomeworkDTO} which contains homeworks of subject and class wrapper in{@link GeneralResponseWrapper}
     * @param idSubject is id of subject
     * @param idClass is id of class
     * @return List of {@link HomeworkDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    @ApiOperation(value = "Teacher gets homework by subject and class", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher can only see the homework of the class where he/she teaches a subject")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idClass, #idSubject)) or hasRole('ADMIN')")
    public GeneralResponseWrapper<List<HomeworkDTO>> getHomeworks(
            @ApiParam(value = "ID of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "ID of class", required = true) @PathVariable int idClass) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getHomework(idSubject,idClass));
    }


    /**
     * Create new homework for transmitted class and lesson.
     * Homework, lesson id and class id required.
     * @param homeworkFileDTO object with homework (homework, lesson id and class id required).
     * @return Created homework for transmitted class and subject in HomeworkDTO
     *         as {@link HomeworkFileDTO} object in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Teacher saves the homework", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher can only give homework for a class where he/she teaches a subject")})})
    @PutMapping("/files")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #homeworkFileDTO.idLesson)) or hasRole('ADMIN')")
    public GeneralResponseWrapper<HomeworkFileDTO> postHomework(
            @ApiParam(value = "Homework object", required = true)@RequestBody HomeworkFileDTO homeworkFileDTO) {
        journalServiceImpl.saveHomework(homeworkFileDTO);
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.NO_CONTENT), null);
    }

    /**
     * Returns object of {@link HomeworkFileDTO} which contains data of file wrapped in{@link GeneralResponseWrapper}
     * @param idLesson is id of lesson
     * @return List of {@link HomeworkFileDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "User gets the homework file", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher", value = "a teacher is allowed to get the homework file of the class where he/she teaches"),
            @ExtensionProperty(name = "user", value = "a pupil is allowed to get his homework")})})
    @GetMapping("/files/{idLesson}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('USER') and @securityExpressionService.isAttendingLesson(principal.id, #idLesson))"
            + " or (hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idLesson))"
            + " or hasRole('ADMIN')")
    public GeneralResponseWrapper<HomeworkFileDTO> getFile(
            @ApiParam(value = "ID of the lesson", required = true) @PathVariable int idLesson){
        System.out.println(journalServiceImpl.getFile(idLesson));
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getFile(idLesson));
    }
}
