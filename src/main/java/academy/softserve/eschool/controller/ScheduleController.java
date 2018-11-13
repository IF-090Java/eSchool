package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.MarkRepository;
import academy.softserve.eschool.service.ScheduleServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The controller {@code ScheduleController} contains methods, that are
 * mapped to special URL patterns (API Endpoints) for working with classes
 * and receives requests from {@link org.springframework.web.servlet.DispatcherServlet}.
 * Methods return raw data back to the client in JSON representations.
 *
 * @author Mariana Vorotniak
 */
@RestController
@RequestMapping("")
@Api(value = "Schedule Endpoint", description = "Crate a schedule for a semester")
@RequiredArgsConstructor
public class ScheduleController {

    @NonNull
    private ScheduleServiceImpl scheduleService;
    @NonNull
    private LessonRepository lessonRepository;
    @NonNull
    private MarkRepository markRepository;

    /**
     * This POST method creates a schedule for a specific class with id {@link academy.softserve.eschool.dto.ClassDTO#id}.
     * Before saving a schedule, the method checks if a schedule with this bounds already exists:
     * <ol>
     *     <li>
     *      If it exists and there are not marks putted on this dates {@link academy.softserve.eschool.model.Mark#lesson},
     *      the method removes the old schedule and creates a new one.
     *      If it exists and there are marks putted on this dates,
     *      the 500 HTTP status code appears on the server and the new schedule will not save.
     *    <li/>
     *    <li>
     *      If it doesn't - the method just create a new schedule.
     *    <li/>
     * <ol/>
     * The method can't create a schedule in the past.
     *
     * @param scheduleDTO   new class object
     * @return              Class of {@link ScheduleDTO} wrapped in {@link GeneralResponseWrapper}
     */
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
    public GeneralResponseWrapper<ScheduleDTO> postSchedule(
            @ApiParam(value = "schedule object", required = true) @RequestBody ScheduleDTO scheduleDTO)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.of(scheduleDTO.getStartOfSemester().getYear(), scheduleDTO.getStartOfSemester().getMonth(), scheduleDTO.getStartOfSemester().getDayOfMonth());
        LocalDate endDate = LocalDate.of(scheduleDTO.getEndOfSemester().getYear(), scheduleDTO.getEndOfSemester().getMonth(), scheduleDTO.getEndOfSemester().getDayOfMonth());
//        IT DOES NOT WORK, TRYING TO RESOLVE IT
//        if(markRepository.getCountOfMarksByDateBounds((startDate).format(formatter), (endDate).format(formatter)) > 0 )
//        {
//            return new GeneralResponseWrapper<>(new Status(409, "Conflict"), null);
//        }
        lessonRepository.deleteScheduleByBounds((startDate).format(formatter), (endDate).format(formatter),
                scheduleDTO.getClassName().getId());
        scheduleService.saveSchedule(scheduleDTO);
        return new GeneralResponseWrapper<>(new Status(201, "OK"), null);
    }
    /**
     * This GET method returns a class of {@link ScheduleDTO} that contains the schedule for a specific class for current week
     * wrapped in {@link GeneralResponseWrapper}
     * @param   classId id of the class which we want to get the schedule
     * @return  Class of {@link ScheduleDTO} wrapped in {@link GeneralResponseWrapper}
     */
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