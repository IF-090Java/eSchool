package academy.softserve.eschool.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasRole('TEACHER')")
    public GeneralResponseWrapper<List<HomeworkDTO>> getHomeworks(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass) {
        //todo bk ++ instead of 3 lines of code use just one. Keep it simple.
        //todo bk use some enum for the response codes. Don't create your own. Use existed one
        return new GeneralResponseWrapper<>(new Status(HttpStatus.OK.value(), "OK"), journalServiceImpl.getHomework(idSubject,idClass));
    }

    @ApiOperation(value = "Save homework")
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Homework successfully created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    public GeneralResponseWrapper<HomeworkDTO> postHomework(
            @ApiParam(value = "homework object", required = true)@RequestBody HomeworkDTO homeworkDTO){
        System.out.println(homeworkDTO);
        System.out.println(new String(homeworkDTO.getFile()));
        return new GeneralResponseWrapper<>(new Status(HttpStatus.CREATED.value(), "Homework successfully created"), homeworkDTO);
    }
}
