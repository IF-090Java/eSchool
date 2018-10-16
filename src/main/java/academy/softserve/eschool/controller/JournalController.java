package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @GetMapping("")
    public List<JournalDTO> getJournals(){
        List<JournalDTO> list = new ArrayList<>();
        list.add(new JournalDTO("Фізика","7-A"));
        list.add(new JournalDTO("Математика","5-Б"));
        list.add(new JournalDTO("Хімія","5-Б"));
        list.add(new JournalDTO("Українська мова","6-Б"));
        list.add(new JournalDTO("Фізика","5-А"));
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
    @GetMapping("/subjects/{id}/classes/{id}")
    public List<JournalMarkDTO> getJournalTable(){
        List<JournalMarkDTO> list = new ArrayList<>();
        list.add(new JournalMarkDTO("Ruslan1 Kharevych1",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{7,0,9,10,1}, new String[]{"s","d","g","s","s"}));
        list.add(new JournalMarkDTO("Ruslan2 Kharevych2",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{10,6,4,0,8}, new String[]{"s","d","g","s","s"}));
        list.add(new JournalMarkDTO("Ruslan3 Kharevych3",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{11,7,8,0,5}, new String[]{"s","d","g","s","s"}));
        list.add(new JournalMarkDTO("Ruslan4 Kharevych4",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{10,0,12,2,1}, new String[]{"s","d","g","s","s"}));
        list.add(new JournalMarkDTO("Ruslan5 Kharevych5",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{10,0,12,0,9}, new String[]{"s","d","g","s","s"}));
        list.add(new JournalMarkDTO("Ruslan6 Kharevych6",new Date[] {new Date(),new Date(),new Date(),new Date(),new Date()},new int[]{0,9,12,10,11}, new String[]{"s","d","g","d","s"}));
        return list;
    }
}
