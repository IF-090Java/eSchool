package academy.softserve.eschool.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.service.ScheduleServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//END POINT  /classes/{id}/schedule

@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
@RequiredArgsConstructor
public class ScheduleController {

    @NonNull
    ScheduleServiceImpl scheduleService;
    @NonNull
    LessonRepository lessonRepository;

    @ApiOperation(value = "Creates a schedule for a class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Schedule successfully created"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    //todo bk write javadoc instead of inline comment
    //todo bk classId is unused variable. IDEA tries to told you about it marking it by grey color. Use it when needed or remove.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/classes/{classId}/schedule")
    public GeneralResponseWrapper<ScheduleDTO> postSchedule(
            @ApiParam(value = "id of class", required = true) @PathVariable("classId") final int classId,
            @ApiParam(value = "schedule object", required = true) @RequestBody ScheduleDTO scheduleDTO)//create a shedule for a class with this id
    {

        LocalDate startDate = scheduleDTO.getStartOfSemester();
        LocalDate endDate = scheduleDTO.getStartOfSemester();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //if schedule with this bounds exists we delete the old schedule and add the new one
        lessonRepository.deleteScheduleByBounds(startDate.format(formatter), endDate.format(formatter), scheduleDTO.getClassName().getId());
        scheduleService.saveSchedule(scheduleDTO);

        return new GeneralResponseWrapper<>(new Status(201, "OK"), null);
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

        return new GeneralResponseWrapper<>(new Status(200, "OK"), scheduleService.getScheduleByClassId(classId));
    }
}