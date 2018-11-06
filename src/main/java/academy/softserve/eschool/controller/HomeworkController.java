package academy.softserve.eschool.controller;
import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//todo bk ++ configure and use the same code styles accross the app. It should be formatted automatically each time
@RestController
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Get homeworks")
public class HomeworkController {
    @Autowired
    JournalServiceImpl journalServiceImpl;

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
        GeneralResponseWrapper<List<HomeworkDTO>> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getHomework(idSubject,idClass));
        return response;
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
    public GeneralResponseWrapper<HomeworkDTO>  postMark(
            @ApiParam(value = "homework object", required = true)@RequestBody HomeworkDTO homeworkDTO){

        //todo bk use some enum for the response codes. Don't create your own. Use existed one
        GeneralResponseWrapper<HomeworkDTO> response;
        response = new GeneralResponseWrapper<>(new Status(201, "OK"), homeworkDTO);
        return response;
    }
}
