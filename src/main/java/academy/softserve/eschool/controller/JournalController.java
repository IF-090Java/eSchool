package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.Journal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<Journal> getJournals(){
        List<Journal> list = new ArrayList<>();
        list.add(new Journal("Фізика","7-A"));
        list.add(new Journal("Математика","5-Б"));
        list.add(new Journal("Хімія","5-Б"));
        list.add(new Journal("Українська мова","6-Б"));
        list.add(new Journal("Фізика","5-А"));
        return list;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 204, message = "No content"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @GetMapping("/subjects/{id}/classess/{id}")
    public void getJournalsTable(){

    }
}
