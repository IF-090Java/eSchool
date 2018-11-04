package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.service.ScheduleServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//END POINT  /classes/{id}/schedule

@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
public class ScheduleController {

    @Autowired
    ScheduleServiceImpl scheduleService;

    @Autowired
    LessonRepository lessonRepository;

    @ApiOperation(value = "Creates a schedule for a class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Schedule successfully created"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/classes/{id_class}/schedule")
    public GeneralResponseWrapper<ScheduleDTO> postSchedule(@PathVariable("id_class") final int id, @RequestBody ScheduleDTO scheduleDTO) throws ParseException//create a shedule for a class with this id
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(scheduleDTO.getStartOfSemester());
        String end = dateFormat.format(scheduleDTO.getEndOfSemester());
        lessonRepository.deleteScheduleByBounds(start, end, scheduleDTO.getClassName().getId());
        scheduleService.saveSchedule(scheduleDTO);
        GeneralResponseWrapper<ScheduleDTO> response;
        response = new GeneralResponseWrapper<>(new Status(201, "OK"), null);
        return response;
    }

    @ApiOperation(value = "Gets schedule for the class with id")
    @GetMapping("/classes/{id_class}/schedule")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public  GeneralResponseWrapper<ScheduleDTO> getSchedule(@PathVariable("id_class") final int id_class){

        GeneralResponseWrapper<ScheduleDTO> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), scheduleService.getScheduleByClassId(id_class));
        return response;
    }
}