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

//todo bk ++ configure and use the same code styles accross the app. It should be formatted automatically each time
@RestController
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Get homeworks")
@RequiredArgsConstructor
public class HomeworkController {

    @NonNull
    private JournalServiceImpl journalServiceImpl;

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
        //todo bk ++ instead of 3 lines of code use just one. Keep it simple.
        //todo bk use some enum for the response codes. Don't create your own. Use existed one
        return new GeneralResponseWrapper<>(new Status(HttpStatus.OK.value(), "OK"), journalServiceImpl.getHomework(idSubject,idClass));
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
                    @ApiResponse(code = 201, message = "Homework successfully created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #homeworkFileDTO.idLesson)")
    public GeneralResponseWrapper<HomeworkFileDTO> postHomework(
            @ApiParam(value = "homework object", required = true)@RequestBody HomeworkFileDTO homeworkFileDTO) {
        journalServiceImpl.saveHomework(homeworkFileDTO);
        return new GeneralResponseWrapper<>(new Status(HttpStatus.CREATED.value(), "Homework successfully created"), null);
    }

    @ApiOperation(value = "Get homework file")
    @GetMapping("/files/{idLesson}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idLesson)")
    public GeneralResponseWrapper<HomeworkFileDTO> getFile(
            @ApiParam(value = "id of lesson", required = true) @PathVariable int idLesson) {
        return new GeneralResponseWrapper<>(new Status(HttpStatus.OK.value(), "OK"), journalServiceImpl.getFile(idLesson));
    }
}
