package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.service.ScheduleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

//END POINT  /classes/{id}/schedule

@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
public class ScheduleController {

   // private static List<ScheduleDTO> list = new ArrayList<>();
    @Autowired
    ScheduleServiceImpl scheduleService;

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
      /*  for(ScheduleDTO scheduleDTO1 : list){
            if (scheduleDTO1.getClassName().getId() == id) list.remove(scheduleDTO1);
        }
        scheduleDTO.setId_schedule(list.size() + 1);
        scheduleDTO.getClassName().setId(id);
        list.add(scheduleDTO);
        return scheduleDTO; //get new schedule*/
      return null;
    }

    @ApiOperation(value = "Gets schedule for the class with id")
    @GetMapping("/classes/{id_class}/schedule")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public ScheduleDTO getSchedule(@PathVariable("id_class") final int id_class){

        return scheduleService.getScheduleByClassId(id_class);
    }
}