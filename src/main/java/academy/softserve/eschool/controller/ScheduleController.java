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

   @ApiOperation(value = "Shows all the shedules")
   public List<ScheduleDTO> getScheduleDTO() throws ParseException {
       SimpleDateFormat format = new SimpleDateFormat("dd.MM");

       Map<Integer, SubjectDTO> map = new HashMap<>();
       map.put(1, new SubjectDTO(1, "Історія України"));

       return Arrays.asList(
               new ScheduleDTO(1, format.parse("01.09"), format.parse("25.12"),
                       new ClassDTO(2,"5-Б","desc"), map, map, map, map, map)
       );
   }

    @ApiOperation(value = "Creates a schedule for a class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Schedule successfully created"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/classes/{id}/schedule")
    public ScheduleDTO postSchedule(@PathVariable("id") final Long id, @RequestBody ScheduleDTO scheduleDTO) throws ParseException//create a shedule for a class with this id
    {
        return getScheduleDTO().get(0);//example
    }

    @GetMapping("/classes/{id}/schedule")
    public ScheduleDTO getSchedule(@PathVariable("id") final int id) throws ParseException
    {
        return new ScheduleDTO(1, new Date(), new Date(),
                new ClassDTO(2,"5-Б","desc"), new HashMap<>(), new HashMap<>()
                , new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

}
