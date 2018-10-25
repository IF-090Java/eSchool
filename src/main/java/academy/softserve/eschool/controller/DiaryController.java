package academy.softserve.eschool.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/diaries")
@Api(value = "Reads students' diaries")
public class DiaryController {
	
	@Autowired
	DiaryServiceBase diaryService;
	
	@GetMapping("/{studentId}")
	@ApiOperation(value = "Get student's diary")
	GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
			@ApiParam(value = "first day of required week", required = true) @RequestParam Date weekStartDate, 
			@ApiParam(value = "id of required student", required = true) @PathVariable Integer studentId){
		
		List<DiaryEntryDTO> diary = diaryService.getDiary(weekStartDate, studentId);
		GeneralResponseWrapper<List<DiaryEntryDTO>> response = 
				new GeneralResponseWrapper<>(new Status(200, "OK"), diary);
		return response;
	}
}
