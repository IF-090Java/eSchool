package academy.softserve.eschool.controller;
import java.util.List;

import academy.softserve.eschool.dto.HomeworkFileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
    @PreAuthorize("hasRole('TEACHER')")
    public GeneralResponseWrapper<List<HomeworkDTO>> getHomeworks(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass) {
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getHomework(idSubject,idClass));
    }

    @ApiOperation(value = "Save homework")
    @PutMapping("/files")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Homework successfully created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    public GeneralResponseWrapper<HomeworkFileDTO> postHomework(
            @ApiParam(value = "homework object", required = true)@RequestBody HomeworkFileDTO homeworkFileDTO){
        journalServiceImpl.saveHomework(homeworkFileDTO);
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.NO_CONTENT), null);
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
    @PreAuthorize("hasRole('TEACHER')")
    public GeneralResponseWrapper<HomeworkFileDTO> getFile(
            @ApiParam(value = "id of lesson", required = true) @PathVariable int idLesson){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getFile(idLesson));
    }

}
