package academy.softserve.eschool.controller;

import java.time.LocalDate;
import java.util.List;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.security.JwtUser;
import academy.softserve.eschool.service.base.DiaryServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/diaries")
@Api(value = "Reads students' diaries", description = "Reads students' diaries")
@RequiredArgsConstructor
public class DiaryController {
    
    @NonNull
    DiaryServiceBase diaryService;
    
    /**
     * Returns list of {@link DiaryEntryDTO} that describe one week of diary wrapped in {@link GeneralResponseWrapper}
     * @param weekStartDate first day of required week
     * @param studentId id of student
     * @return List of {@link DiaryEntryDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get student's diary")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
            @ApiParam(value = "first day of week, accepts date in format 'yyyy-MM-dd'", required=true) @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate weekStartDate){
        //todo bk ++ instead of 3 lines of code use just one. Keep it simple.
        //return new GeneralResponseWrapper<>(new Status(200, "OK"), diaryService.getDiary(weekStartDate, studentId))
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();
    //todo bk Use such stile across the whole app. Looks much simpler and easier for reading
    return new GeneralResponseWrapper<>(Status.of(OK), diaryService.getDiary(weekStartDate, user.getId().intValue()));
    }
}
