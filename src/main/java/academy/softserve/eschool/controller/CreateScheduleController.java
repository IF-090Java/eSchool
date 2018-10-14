package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.CreateScheduleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mariana on 12.10.2018.
 */
//END POINT  /classes/{id}/schedule

@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
public class CreateScheduleController {

   /* @Query(value = "INSERT INTO schedule(id_schedule, id_grade, id_subject) VALUES CreateScheduleDTO.id, (SELECT id  FROM grade" +
            "WHERE gname = CreateScheduleDTO.theClass), (SELECT id WHERE sname IN ...)")
   */
   @ApiOperation(value = "Shows all the shedules")
   @GetMapping("/classes/schedule")//this method can be deleted
   public List<CreateScheduleDTO> getCreateScheduleDTO() throws ParseException {
       SimpleDateFormat format = new SimpleDateFormat("dd.MM");

       Map<Integer, String> map = new HashMap<>();
       map.put(1, "Біологія");

       return Arrays.asList(
               new CreateScheduleDTO(1, format.parse("01.09"), format.parse("25.12"), "5-B",
                       map, map, map, map, map)
       );
   }

    @ApiOperation(value = "Creates a schedule for a class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Розклад успішно створений"),
                    @ApiResponse(code = 500, message = "Помилка сервера")
            }
    )
    @PostMapping("/classes/{id}/schedule")
    public CreateScheduleDTO postSchedule(@PathVariable("id") final String id, @RequestBody CreateScheduleDTO createScheduleDTO) throws ParseException//create a shedule for a class with this id
    {
        return getCreateScheduleDTO().get(0);//example
        //return createScheduleDTO;
    }
}
