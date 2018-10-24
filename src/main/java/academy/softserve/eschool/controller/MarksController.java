package academy.softserve.eschool.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.repository.MarkRepository;
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
	private MarkRepository markRepo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping("")
	@ApiOperation(value = "Get students' marks")
	GeneralResponseWrapper<List<MarkDataPointDTO>> getMarks (
			@ApiParam(value = "filter results by student id", required = false) @RequestParam(value = "student_id", required = false) Integer studentId,
			@ApiParam(value = "filter results by subject id", required = false) @RequestParam(value = "subject_id", required = false) Integer subjectId,
			@ApiParam(value = "filter results by class id", required = false) @RequestParam(value = "class_id", required = false) Integer classId,
			@ApiParam(value = "get marks received after specified date", required = false) @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodStart,
			@ApiParam(value = "get marks received before specified date", required = false) @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodEnd){
		
		String startDate = null;
		String endDate = null;
		
		if (periodStart != null) {
			startDate = dateFormat.format(periodStart);
		}
		if (periodEnd != null) {
			endDate = dateFormat.format(periodEnd);
		}
		List<Object[]> marks = markRepo.findAll(subjectId, classId, studentId, startDate, endDate);
		List<MarkDataPointDTO> dataPoints = formDataPoints(marks);
		GeneralResponseWrapper<List<MarkDataPointDTO>> response = 
				new GeneralResponseWrapper<>(new Status(200, "ok"), dataPoints);
		return response;
	}

	private List<MarkDataPointDTO> formDataPoints(List<Object[]> data) {
		List<MarkDataPointDTO> dataPoints;
		dataPoints = data.stream().map((obj) -> {
				double averageMark = ((BigDecimal)obj[0]).doubleValue();
				Date date = (Date)obj[1];
				return new MarkDataPointDTO(averageMark, date);
			})
			.collect(Collectors.toList());
		Collections.sort(dataPoints);
		System.out.println(dataPoints.toString());
		return dataPoints;
	}
}
