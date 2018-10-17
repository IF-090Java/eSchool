package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        list.add(new JournalDTO(1,2,"Фізика","7-A"));
        list.add(new JournalDTO(1,2,"Математика","5-Б"));
        list.add(new JournalDTO(1,2,"Хімія","5-Б"));
        list.add(new JournalDTO(1,2,"Українська мова","6-Б"));
        list.add(new JournalDTO(1,2,"Фізика","5-А"));
        return list;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public List<JournalMarkDTO> getJournalTable(){
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
