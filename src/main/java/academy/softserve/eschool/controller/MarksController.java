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

import academy.softserve.eschool.dto.MarkDTO;

@RestController
@RequestMapping("/marks")
public class MarksController {
	
	@GetMapping("")
	List<MarkDTO> getMarks (
			@RequestParam(value = "student_id", required = false) Integer studentId,
			@RequestParam(value = "subject_id", required = false) Integer subjectId,
			@RequestParam(value = "class_id", required = false) Integer classId,
			@RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodStart,
			@RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodEnd,
			HttpServletResponse response){
		
		//TODO get marks filtered according to request parameters
		List<MarkDTO> marks = createStatisticsStub();
		List<MarkDTO> filteredMarks = marks.stream()
				.filter((mark) -> studentId == null ? true : mark.getStudentId() == studentId)
				.filter((mark) -> subjectId == null ? true : mark.getSubjectId() == subjectId)
				.filter((mark) -> classId == null ? true : mark.getClassId() == classId)
				.filter((mark) -> periodStart == null ? true : mark.getDate().after(periodStart))
				.filter((mark) -> periodEnd == null ? true : mark.getDate().before(periodEnd))
				.collect(Collectors.toList());
		response.addHeader("Access-Control-Allow-Origin", "*");
		Collections.sort(filteredMarks);
		return filteredMarks;
	}

	private List<MarkDTO> createStatisticsStub() {
		List<MarkDTO> marks = new ArrayList<>();
		Random r = new Random(42);
		for (int i = 0; i < 100; i++) {
			int studentId = r.nextInt(10);
			int subjectId = r.nextInt(3);
			int classId = studentId%2;
			int mark = 4 + r.nextInt(9);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DAY_OF_YEAR, r.nextInt(14));
			marks.add(new MarkDTO(studentId, subjectId, classId, mark, calendar.getTime()));
			
		}
		return marks;
	}
	
}
