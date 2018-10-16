package academy.softserve.eschool.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/diaries")
@Api(value = "Reads students' diaries")
public class DiaryController {
	@GetMapping("/{studentId}")
	@ApiOperation(value = "Get student's diary")
	List<DiaryEntryDTO> getDiary(
			@ApiParam(value = "first day of required week", required = true) @RequestParam Date weekStartDate, 
			@ApiParam(value = "id of required student", required = true) @PathVariable Integer studentId){
		//TODO get diary for student with id=studentId from database
		return createDiaryStub(weekStartDate);
	}
	
	private List<DiaryEntryDTO> createDiaryStub (Date weekStartDate){
		Random r = new Random(weekStartDate.getTime());
		Random l = new Random(42);
		Date date;
		String[] lessons = {"Математика", "Біологія", "Фізика", "Історія", "Хімія", "Англійська", "Українська", "Фізкультура"};
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(weekStartDate);
		List<DiaryEntryDTO> diary = new ArrayList<>();
		
		for (int i = 0; i<5; i++) {
			int lessonAmount = 3+l.nextInt(4);
			date = cal.getTime();
			for (int j = 1; j<=lessonAmount; j++) {
				String lesson = null;
				String homework = null;
				int mark = 0;
				String note = null;
				lesson = lessons[l.nextInt(lessons.length)];
				if (r.nextFloat() > 0.5) {
					homework = String.format("Домашнє завдання #%d, #%d", r.nextInt(100), r.nextInt(100));
				}
				if (r.nextDouble() > 0.7) {
					mark = 4 + r.nextInt(9);
				}
				if (mark > 10) {
					note = "Молодець!";
				} else if (mark < 6 && mark > 0) {
					note = "Не готовий!";
				}
				DiaryEntryDTO entry = new DiaryEntryDTO(date, j, lesson, homework, mark, note);
				diary.add(entry);
			}
			cal.add(GregorianCalendar.DAY_OF_WEEK, 1);
		}
		
		return diary;
	}
}
