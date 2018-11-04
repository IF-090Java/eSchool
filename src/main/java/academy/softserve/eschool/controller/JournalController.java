package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Journal's Endpoint", description = "Get journals")
@RequestMapping("/journals")
public class JournalController {
    @Autowired
    JournalServiceImpl journalServiceImpl;

    @ApiOperation(value = "Get list of journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<JournalDTO> getJournals(){
        return journalServiceImpl.getJournals();
    }

    @ApiOperation(value = "Get list of journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teachers/{idTeacher}")
    public List<JournalDTO> getJournalsTeacher(@PathVariable int idTeacher){
        return journalServiceImpl.getJournalsByTeacher(idTeacher);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public List<JournalMarkDTO> getJournalTable(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
            ){
        return journalServiceImpl.getJournal(idSubject,idClass);
    }
}
