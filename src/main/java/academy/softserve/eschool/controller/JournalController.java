package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @GetMapping("")
    public List<JournalDTO> getJournals(){
        List<JournalDTO> list = new ArrayList<>();
        list.add(new JournalDTO(1,1,"Історія України","5-A",2018));
        list.add(new JournalDTO(4,2,"Українська мова","5-Б",2014));
        list.add(new JournalDTO(3,2,"Англійська мова","5-Б",2015));
        list.add(new JournalDTO(2,3,"Інформатика","5-В",2016));
        list.add(new JournalDTO(1,4,"Історія України","6-А",2018));
        return list;
    }

    @ApiOperation(value = "Get list of journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @GetMapping("/teachers/{idTeacher}")
    public List<JournalDTO> getJournalsTeacher(@PathVariable int idTeacher){
        return journalServiceImpl.getSubjectsByTeacher(idTeacher);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public List<JournalMarkDTO> getJournalTable(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
            ){
        List<JournalMarkDTO> list = new ArrayList<>();
        list.add(new JournalMarkDTO("Руслан Харевич",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(10,new Date(),"к"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(2,new Date(),"к"),
                              new MarkDescriptionDTO(3,new Date(),"c"),
                              new MarkDescriptionDTO(7,new Date(),"т"),
                              new MarkDescriptionDTO(11,new Date(),"c")))));

        list.add(new JournalMarkDTO("Сеньків Олег",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(1,new Date(),"к"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(null,new Date(),"к"),
                              new MarkDescriptionDTO(9,new Date(),"c"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(7,new Date(),"c")))));

        list.add(new JournalMarkDTO("Яремин Андрій",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(12,new Date(),"к"),
                              new MarkDescriptionDTO(5,new Date(),"т"),
                              new MarkDescriptionDTO(2,new Date(),"к"),
                              new MarkDescriptionDTO(null,new Date(),"c"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(11,new Date(),"c")))));

        list.add(new JournalMarkDTO("Табачнюк Мирослав",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(10,new Date(),"к"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(8,new Date(),"к"),
                              new MarkDescriptionDTO(6,new Date(),"c"),
                              new MarkDescriptionDTO(7,new Date(),"т"),
                              new MarkDescriptionDTO(null,new Date(),"c")))));

        list.add(new JournalMarkDTO("Романяк Марія",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(null,new Date(),"к"),
                              new MarkDescriptionDTO(null,new Date(),"т"),
                              new MarkDescriptionDTO(10,new Date(),"к"),
                              new MarkDescriptionDTO(5,new Date(),"c"),
                              new MarkDescriptionDTO(2,new Date(),"т"),
                              new MarkDescriptionDTO(null,new Date(),"c")))));
        return list;
    }
}
