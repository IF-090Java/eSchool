package academy.softserve.eschool.controller;
import academy.softserve.eschool.dto.HomeworkDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/homeworks")
@Api(value = "Homework's Endpoint", description = "Get homeworks")
public class HomeworkController {

    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    @ApiOperation(value = "Get homeworks by subjects and classes")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public List<HomeworkDTO> getHomeworks(
            @ApiParam(value = "first day of required week", required = true) @RequestParam Date start,
            @ApiParam(value = "first day of required week", required = true) @RequestParam Date end,
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
    ){
        List<HomeworkDTO> list = new ArrayList<>();
        list.add(new HomeworkDTO(new Date(),"p.125","file1.txt"));
        list.add(new HomeworkDTO(new Date(),"p.122","file2.txt"));
        list.add(new HomeworkDTO(new Date(),"ex.2","file3.txt"));
        list.add(new HomeworkDTO(new Date(),"reading","file4.txt"));
        list.add(new HomeworkDTO(new Date(),"p.12 , ex.2","file5.txt"));
        return list;
    }
}
