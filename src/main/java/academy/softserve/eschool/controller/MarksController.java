package academy.softserve.eschool.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.service.base.MarkServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/marks")
@Api(value = "Reads studets' marks")
public class MarksController {
	
	@Autowired
	private MarkServiceBase markService;
	
	@GetMapping("")
	@ApiOperation(value = "Get marks by date filtered by specified params")
	GeneralResponseWrapper<List<MarkDataPointDTO>> getMarks (
			@ApiParam(value = "filter results by student id", required = false) @RequestParam(value = "student_id", required = false) Integer studentId,
			@ApiParam(value = "filter results by subject id", required = false) @RequestParam(value = "subject_id", required = false) Integer subjectId,
			@ApiParam(value = "filter results by class id", required = false) @RequestParam(value = "class_id", required = false) Integer classId,
			@ApiParam(value = "get marks received after specified date", required = false) @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodStart,
			@ApiParam(value = "get marks received before specified date", required = false) @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodEnd){
		
		GeneralResponseWrapper<List<MarkDataPointDTO>> response;
		List<MarkDataPointDTO> dataPoints = markService.getFilteredByParams(subjectId, classId, studentId, periodStart, periodEnd);
		if (!dataPoints.isEmpty()) {
			response = new GeneralResponseWrapper<>(new Status(200, "OK"), dataPoints);
		} else {
			response = new GeneralResponseWrapper<>(new Status(204, "No data for this request"), dataPoints);
		}
		return response;
	}

}
