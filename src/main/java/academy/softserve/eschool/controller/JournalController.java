package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Journal's operations", description = "Get journals")
@RequestMapping("/journals")
public class JournalController {
    @Autowired
    JournalServiceImpl journalServiceImpl;

    @ApiOperation(value = "Get list of all journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @GetMapping("")
    public GeneralResponseWrapper<List<JournalDTO>> getJournals(){
        GeneralResponseWrapper<List<JournalDTO> > response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournals());
        return response;
    }

    @ApiOperation(value = "Get list of all teacher's journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @GetMapping("/teachers/{idTeacher}")
    public GeneralResponseWrapper<List<JournalDTO>> getJournalsTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        GeneralResponseWrapper<List<JournalDTO>> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournalsByTeacher(idTeacher));
        return response;
    }

    @ApiOperation(value = "Get list of active teacher's journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @GetMapping("/teachers/{idTeacher}/active")
    public GeneralResponseWrapper<List<JournalDTO>> getActiveJournalsTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        GeneralResponseWrapper<List<JournalDTO>> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getActiveJournalsByTeacher(idTeacher));
        return response;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public GeneralResponseWrapper<List<JournalMarkDTO>> getJournalTable(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
            ){
        GeneralResponseWrapper<List<JournalMarkDTO>> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournal(idSubject,idClass));
        return response;
    }
}
