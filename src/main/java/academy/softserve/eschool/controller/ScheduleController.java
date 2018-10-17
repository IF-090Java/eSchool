package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.ScheduleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.jpa.repository.Query;
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

   /* @Query(value = "INSERT INTO schedule(id_schedule, id_grade, id_subject) VALUES CreateScheduleDTO.id, (SELECT id  FROM grade" +
            "WHERE gname = CreateScheduleDTO.theClass), (SELECT id WHERE sname IN ...)")
   */
   @ApiOperation(value = "Shows all the shedules")
   public List<ScheduleDTO> getScheduleDTO() throws ParseException {
       SimpleDateFormat format = new SimpleDateFormat("dd.MM");

       Map<Integer, String> map = new HashMap<>();
       map.put(1, "Біологія");

       return Arrays.asList(
               new ScheduleDTO(1, format.parse("01.09"), format.parse("25.12"),
                       new ClassDTO(1,8, "Б", "Класний керівник - Кашуба Григорій"),
                       map, map, map, map, map)
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
        //return scheduleDTO;
    }

    @GetMapping("/classes/{id_class}/schedule")
    public ScheduleDTO getSchedule(@PathVariable("id_class") final int id_class) throws ParseException
    {
        return new ScheduleDTO(1, new Date(), new Date(),
                new ClassDTO(id_class,8, "Б", "Класний керівник - Кашуба Григорій"), new HashMap<>(), new HashMap<>()
                , new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

}
