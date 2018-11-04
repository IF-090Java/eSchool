package academy.softserve.eschool.controller;
import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Get homeworks")
public class HomeworkController {
    @Autowired
    JournalServiceImpl journalServiceImpl;

    @ApiOperation(value = "Get homeworks by subjects and classes")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public List<HomeworkDTO> getHomeworks(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass) {
       return journalServiceImpl.getHomework(idSubject,idClass);
    }
}
