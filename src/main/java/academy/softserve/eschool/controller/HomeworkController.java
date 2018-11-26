package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
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
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Get homeworks")
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
    @ApiOperation(value = "Get homeworks by subject and class")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idClass, #idSubject)")
    public GeneralResponseWrapper<List<HomeworkDTO>> getHomeworks(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getHomework(idSubject,idClass));
    }

    /**
     * Create new homework for transmitted class and lesson.
     * Homework, lesson id and class id required.
     * @param homeworkFileDTO object with homework (homework, lesson id and class id required).
     * @return Created homework for transmitted class and subject in HomeworkDTO
     *         as {@link HomeworkFileDTO} object in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Save homework")
    @PutMapping("/files")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #homeworkFileDTO.idLesson)")
    public GeneralResponseWrapper<HomeworkFileDTO> postHomework(
            @ApiParam(value = "homework object", required = true)@RequestBody HomeworkFileDTO homeworkFileDTO) {
        journalServiceImpl.saveHomework(homeworkFileDTO);
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.NO_CONTENT), null);
    }

    /**
     * Returns object of {@link HomeworkFileDTO} which contains data of file wrapped in{@link GeneralResponseWrapper}
     * @param idLesson is id of lesson
     * @return List of {@link HomeworkFileDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "Get homework file")
    @GetMapping("/files/{idLesson}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('USER') and @securityExpressionService.isAttendingLesson(principal.id, #idLesson))"
            + " or (hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idLesson))")
    public GeneralResponseWrapper<HomeworkFileDTO> getFile(
            @ApiParam(value = "id of lesson", required = true) @PathVariable int idLesson){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getFile(idLesson));
    }
}
