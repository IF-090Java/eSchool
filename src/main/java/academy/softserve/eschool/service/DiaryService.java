package academy.softserve.eschool.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.service.base.DiaryServiceBase;

@Service
public class DiaryService implements DiaryServiceBase{

	@Autowired
	private LessonRepository lessonRepo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<DiaryEntryDTO> getDiary(Date weekStartDate, int studentId) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(weekStartDate);
		cal.add(GregorianCalendar.DAY_OF_MONTH, 4);
		Date weekEndDate = cal.getTime();
		String startDate = dateFormat.format(weekStartDate);
		String endDate = dateFormat.format(weekEndDate);
		List<Map<String, Object>> diaryData = lessonRepo.getDiary(studentId, startDate, endDate);
		List<DiaryEntryDTO> diary = diaryData.stream().map((obj) -> {
					Date date = (Date)obj.get("date");
					byte no = (byte)obj.get("lesson_number");
					String lessonName = (String)obj.get("name");
					String hometask = obj.get("hometask") == null ? "" :(String)obj.get("hometask");
					byte mark = obj.get("mark") == null ? 0 : (byte)obj.get("mark");
					String note = obj.get("note") == null ? "" : (String)obj.get("note");
					return new DiaryEntryDTO(date, no, lessonName, hometask, mark, note);
				})
				.collect(Collectors.toList());
		System.out.println(diary);
		return diary;
	}

}
