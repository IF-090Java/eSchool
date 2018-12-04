package academy.softserve.eschool.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.service.base.DiaryServiceBase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService implements DiaryServiceBase {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NonNull
    private LessonRepository lessonRepo;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Returns list of {@link DiaryEntryDTO} that describe one week of diary
     * @param weekStartDate first day of required week
     * @param studentId id of student
     * @return List of {@link DiaryEntryDTO}
     */
    @Override
    public List<DiaryEntryDTO> getDiary(LocalDate weekStartDate, int studentId) {
        LocalDate weekEndDate = weekStartDate.plusDays(5);
        String startDate = dateFormat.format(weekStartDate);
        String endDate = dateFormat.format(weekEndDate);
        logger.debug("Reading diary data for user_id : [{}] period : [{} - {}]", studentId, startDate, endDate);
        List<Map<String, Object>> diaryData = lessonRepo.getDiary(studentId, startDate, endDate);
        return mapToDTO(diaryData);
    }
    
    private List<DiaryEntryDTO> mapToDTO(List<Map<String, Object>> diaryData) {
        return diaryData.stream().map((obj) -> {
            int lessonId = (int)obj.get("id");
            Integer homeworkFileId = (Integer)obj.get("homework_file_id");
            LocalDate date = ((Date)obj.get("date")).toLocalDate();
            byte no = (byte)obj.get("lesson_number");
            String lessonName = (String)obj.get("name");
            String hometask = obj.get("hometask") == null ? "" :(String)obj.get("hometask");
            byte mark = obj.get("mark") == null ? 0 : (byte)obj.get("mark");
            String note = obj.get("note") == null ? "" : (String)obj.get("note");
            return new DiaryEntryDTO(lessonId, date, no, lessonName, hometask, homeworkFileId, mark, note);
        })
        .collect(Collectors.toList());
    }

}
