package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.security.JwtUser;
import academy.softserve.eschool.service.base.DiaryServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/diaries")
@Api(value = "Diary endpoint", description = "Operation for reading students' diaries")
@RequiredArgsConstructor
public class DiaryController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NonNull
    private DiaryServiceBase diaryService;

    /**
     * Returns list of {@link DiaryEntryDTO} that describe one week of diary wrapped
     * in {@link GeneralResponseWrapper}
     * @param weekStartDate first day of required week
     * @return List of {@link DiaryEntryDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "User gets student's diary", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "user", value = "every pupil is allowed to see his own diary")})})
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
            @ApiParam(value = "first day of week, accepts date in format 'yyyy-MM-dd'", required=true) @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate weekStartDate){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();
        logger.debug("Called getDiary() for weekStartDate : [{}]", weekStartDate);
        return new GeneralResponseWrapper<>(Status.of(OK), diaryService.getDiary(weekStartDate, user.getId().intValue()));
    }
}
