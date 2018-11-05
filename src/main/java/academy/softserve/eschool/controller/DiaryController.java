package academy.softserve.eschool.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.service.base.DiaryServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/diaries")
@Api(value = "Reads students' diaries", description="Reads students' diaries")
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
	@GetMapping("/{studentId}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Get student's diary")
	GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
			@ApiParam(value = "first day of required week, accepts date in format 'yyyy-MM-dd'") @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate weekStartDate, 
			@ApiParam(value = "id of required student") @PathVariable Integer studentId){
		//todo bk ++ instead of 3 lines of code use just one. Keep it simple.
		//return new GeneralResponseWrapper<>(new Status(200, "OK"), diaryService.getDiary(weekStartDate, studentId))
		return new GeneralResponseWrapper<>(new Status(HttpStatus.OK.value(), "OK"), diaryService.getDiary(weekStartDate, studentId));
	}
}
