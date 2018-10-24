package academy.softserve.eschool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.repository.LessonRepository;
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
	private LessonRepository lessonRepo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping("/{studentId}")
	@ApiOperation(value = "Get student's diary")
	GeneralResponseWrapper<List<DiaryEntryDTO>> getDiary(
			@ApiParam(value = "first day of required week", required = true) @RequestParam Date weekStartDate, 
			@ApiParam(value = "id of required student", required = true) @PathVariable Integer studentId){
		//TODO get diary for student with id=studentId from database
		List<DiaryEntryDTO> diary = getDiaryEntries(weekStartDate, studentId);
		GeneralResponseWrapper<List<DiaryEntryDTO>> response = 
				new GeneralResponseWrapper<>(new Status(200, "OK"), diary);
		return response;
	}

	private List<DiaryEntryDTO> getDiaryEntries(Date weekStartDate, Integer studentId) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(weekStartDate);
		cal.add(GregorianCalendar.DAY_OF_MONTH, 4);
		Date weekEndDate = cal.getTime();
		String startDate = dateFormat.format(weekStartDate);
		String endDate = dateFormat.format(weekEndDate);
		System.out.println(startDate + " " + endDate);
		List<Object[]> diaryData = lessonRepo.getDiary(studentId, startDate, endDate);
		for (Object[] obj : diaryData) {
			for (int i = 0; i < obj.length; i++) {
				System.out.print(obj[i] + " ");
			}
			System.out.println("");
		}
		List<DiaryEntryDTO> diary = diaryData.stream().map((obj) -> {
					byte no = (Byte)obj[1];
					byte mark = 0;
					if (obj[4] != null) {
						mark = (Byte)obj[4];
					}
					return new DiaryEntryDTO((Date)obj[0], (int)no, (String)obj[2], (String)obj[3], (int)mark, (String)obj[5]);
				})
				.collect(Collectors.toList());
		System.out.println(diary);
		return diary;
	}
}
