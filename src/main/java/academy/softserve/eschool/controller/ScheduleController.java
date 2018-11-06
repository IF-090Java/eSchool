package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.service.ScheduleServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/classes/{classId}/schedule")
    //todo bk ++
    public GeneralResponseWrapper<ScheduleDTO> postSchedule(
            @ApiParam(value = "id of class", required = true) @PathVariable("classId") final int classId,
            @ApiParam(value = "schedule object", required = true) @RequestBody ScheduleDTO scheduleDTO)//create a shedule for a class with this id
    {
        //todo bk
        LocalDate startDate = scheduleDTO.getStartOfSemester();
        LocalDate endDate = scheduleDTO.getStartOfSemester();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //if schedule with this bounds exists we delete the old schedule and add the new one
        lessonRepository.deleteScheduleByBounds(startDate.format(formatter), endDate.format(formatter), scheduleDTO.getClassName().getId());
        scheduleService.saveSchedule(scheduleDTO);
        GeneralResponseWrapper<ScheduleDTO> response = new GeneralResponseWrapper<>(new Status(201, "OK"), null);
        return response;
    }

    @ApiOperation(value = "Gets schedule for the class with id")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/classes/{classId}/schedule")
    public GeneralResponseWrapper<ScheduleDTO> getSchedule(@ApiParam(value = "id of class", required = true) @PathVariable("classId") final int classId){

        GeneralResponseWrapper<ScheduleDTO> response = new GeneralResponseWrapper<>(new Status(200, "OK"), scheduleService.getScheduleByClassId(classId));
        return response;
    }
}