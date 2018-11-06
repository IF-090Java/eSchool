package academy.softserve.eschool.controller;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.service.base.DiaryServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;

@RestController
@RequestMapping("/diaries")
@Api(value = "Reads students' diaries", description="Reads students' diaries")
public class DiaryController {
	
	@Autowired
	DiaryServiceBase diaryService;
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Get student's diary")
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{studentId}")
	GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
			@ApiParam(value = "first day of required week, accepts date in format 'MMM dd yyyy'", required = true) @RequestParam Date weekStartDate,
			@ApiParam(value = "id of required student", required = true) @PathVariable Integer studentId){
		
		List<DiaryEntryDTO> diary = diaryService.getDiary(weekStartDate, studentId);
		GeneralResponseWrapper<List<DiaryEntryDTO>> response;
		response = new GeneralResponseWrapper<>(new Status(200, "OK"), diary);

		//todo bk ++ instead of 3 lines of code use just one. Keep it simple.
		//return new GeneralResponseWrapper<>(new Status(200, "OK"), diaryService.getDiary(weekStartDate, studentId))
		return response;
	}
}
