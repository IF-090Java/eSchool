package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.dto.SubjectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Mariana on 12.10.2018.
 */
//END POINT  /classes/{id}/schedule

@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
public class ScheduleController {

    private static List<ScheduleDTO> list = new ArrayList<>();

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    static {
       Map<Integer, SubjectDTO> map = new HashMap<>();
       map.put(1, new SubjectDTO(1, "Історія України"));

        try {
            list.add(new ScheduleDTO(1, format.parse("2018-10-18"), format.parse("2018-12-18"),
                    new ClassDTO(1,"5-A","Класний керівник - Данилишин Богдан"), map, map, map, map, map));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = "Creates a schedule for a class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Schedule successfully created"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/classes/{id_class}/schedule")
    public ScheduleDTO postSchedule(@PathVariable("id_class") final int id, @RequestBody ScheduleDTO scheduleDTO) throws ParseException//create a shedule for a class with this id
    {
        for (ScheduleDTO scheduleDTO1: list)
        {
            if (scheduleDTO1.getClassName().getId() == id) return scheduleDTO1;
        }

        list.add(new ScheduleDTO(1, format.parse("2018-10-18"), format.parse("2018-12-18"),
                new ClassDTO(id,"5-A","Класний керівник - Данилишин Богдан"), new HashMap<Integer, SubjectDTO>(),
                new HashMap<Integer, SubjectDTO>(), new HashMap<Integer, SubjectDTO>(), new HashMap<Integer, SubjectDTO>(),
                new HashMap<Integer, SubjectDTO>()));

        return list.get(list.size() - 1); //get new connection
    }

    @ApiOperation(value = "Gets schedule for the class with id")
    @GetMapping("/classes/{id_class}/schedule")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public ScheduleDTO getSchedule(@PathVariable("id_class") final int id_class) throws ParseException
    {
        for(ScheduleDTO scheduleDTO : list){
            if (scheduleDTO.getClassName().getId() == id_class) return scheduleDTO;
        }
        return new ScheduleDTO(1, new Date(), new Date(),
                new ClassDTO(id_class,"5-A","Класний керівник - Данилишин Богдан"), new HashMap<>(), new HashMap<>()
                , new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

}
