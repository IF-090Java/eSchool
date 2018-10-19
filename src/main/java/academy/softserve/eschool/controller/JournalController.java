package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "Journal's Endpoint", description = "Get journals")
@RequestMapping("/journals")
public class JournalController {

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
        list.add(new JournalDTO(1,1,"Історія України","5-A"));
        list.add(new JournalDTO(4,2,"Українська мова","5-Б"));
        list.add(new JournalDTO(3,2,"Англійська мова","5-Б"));
        list.add(new JournalDTO(2,3,"Інформатика","5-В"));
        list.add(new JournalDTO(1,4,"Історія України","6-А"));
        return list;
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
            @ApiParam(value = "first day of required week", required = true) @RequestParam Date start,
            @ApiParam(value = "first day of required week", required = true) @RequestParam Date end,
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
            ){
        List<JournalMarkDTO> list = new ArrayList<>();
        list.add(new JournalMarkDTO("RuslanKharevych1",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(10,new Date(),"a"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(2,new Date(),"a"),
                              new MarkDescriptionDTO(3,new Date(),"c"),
                              new MarkDescriptionDTO(7,new Date(),"b"),
                              new MarkDescriptionDTO(11,new Date(),"c")))));

        list.add(new JournalMarkDTO("RuslanKharevych2",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(1,new Date(),"a"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(null,new Date(),"a"),
                              new MarkDescriptionDTO(9,new Date(),"c"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(7,new Date(),"c")))));

        list.add(new JournalMarkDTO("RuslanKharevych3",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(12,new Date(),"a"),
                              new MarkDescriptionDTO(5,new Date(),"b"),
                              new MarkDescriptionDTO(2,new Date(),"a"),
                              new MarkDescriptionDTO(null,new Date(),"c"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(11,new Date(),"c")))));

        list.add(new JournalMarkDTO("RuslanKharevych4",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(10,new Date(),"a"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(8,new Date(),"a"),
                              new MarkDescriptionDTO(6,new Date(),"c"),
                              new MarkDescriptionDTO(7,new Date(),"b"),
                              new MarkDescriptionDTO(null,new Date(),"c")))));

        list.add(new JournalMarkDTO("RuslanKharevych5",new ArrayList<>(
                Arrays.asList(new MarkDescriptionDTO(null,new Date(),"a"),
                              new MarkDescriptionDTO(null,new Date(),"b"),
                              new MarkDescriptionDTO(10,new Date(),"a"),
                              new MarkDescriptionDTO(5,new Date(),"c"),
                              new MarkDescriptionDTO(2,new Date(),"b"),
                              new MarkDescriptionDTO(null,new Date(),"c")))));
        return list;
    }
}
