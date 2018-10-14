package academy.softserve.eschool.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.DiaryEntryDTO;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
	@GetMapping("/{studentId}")
	List<DiaryEntryDTO> getDiary(
			@RequestParam(defaultValue = "0") Integer offset, 
			@PathVariable Integer studentId){
		//TODO get diary for student with id=studentId from database
		return createDiaryStub(offset);
	}
	
	private List<DiaryEntryDTO> createDiaryStub (int offset){
		Random r = new Random(42);
		Date date;
		String[] lessons = {"Математика", "Біологія", "Фізика", "Історія", "Хімія", "Англійська"};
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(GregorianCalendar.WEEK_OF_MONTH, offset);
		cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		List<DiaryEntryDTO> diary = new ArrayList<>();
		
		for (int i = 0; i<5; i++) {
			int lessonAmount = 3+r.nextInt(4);
			date = cal.getTime();
			for (int j = 1; j<=lessonAmount; j++) {
				String lesson = null;
				String homework = null;
				int mark = 0;
				String note = null;
				lesson = lessons[r.nextInt(lessons.length)];
				if (r.nextFloat() > 0.5) {
					homework = String.format("home work #%d", r.nextInt(100));
				}
				if (r.nextDouble() > 0.7) {
					mark = r.nextInt(13);
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
