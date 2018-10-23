package academy.softserve.eschool.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/marks")
@Api(value = "Reads studets' marks")
public class MarksController {
	
	@GetMapping("")
	@ApiOperation(value = "Get students' marks")
	GeneralResponseWrapper<List<MarkDataPointDTO>> getMarks (
			@ApiParam(value = "filter results by student id", required = false) @RequestParam(value = "student_id", required = false) Integer studentId,
			@ApiParam(value = "filter results by subject id", required = false) @RequestParam(value = "subject_id", required = false) Integer subjectId,
			@ApiParam(value = "filter results by class id", required = false) @RequestParam(value = "class_id", required = false) Integer classId,
			@ApiParam(value = "get marks received after specified date", required = false) @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodStart,
			@ApiParam(value = "get marks received before specified date", required = false) @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodEnd){
		
		//TODO get marks filtered according to request parameters
		List<MarkDataPointDTO> marks = createStatisticsStub();
		GeneralResponseWrapper<List<MarkDataPointDTO>> response = 
				new GeneralResponseWrapper<>(new Status(200, "ok"), marks);
		return response;
	}

	private List<MarkDataPointDTO> createStatisticsStub() {
		List<MarkDataPointDTO> marks = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 15; i++) {
			int mark = 4 + r.nextInt(9);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DAY_OF_YEAR, i-14);
			marks.add(new MarkDataPointDTO(mark, calendar.getTime()));
			
		}
		return marks;
	}
	
}
