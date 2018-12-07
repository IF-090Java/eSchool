package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.DiaryEntryDTO;
import academy.softserve.eschool.repository.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DiaryServiceTest {
    private String NOTE_KEY = "note";
    private String MARK_KEY = "mark";
    private String HOMETASK_KEY = "hometask";
    private String NAME_KEY = "name";
    private String LESSON_NUMBER_KEY = "lesson_number";
    private String DATE_KEY = "date";
    private String HOMEWORK_FILE_ID_KEY = "homework_file_id";
    private String ID_KEY = "id";
    
    private int studentId;
    private String startDate;
    private String endDate;
    private int entryId;
    private Integer homeworkFileId;
    private byte lessonNumber;
    private String lessonName;
    private String homework;
    private Byte mark;
    private String note;
    
    @InjectMocks
    private DiaryService diaryService;
    
    @Mock
    private LessonRepository lessonRepository;
    
    @Test
    public void serviceShouldCorrectlyBindRepositoryResultToDTO() {
        studentId = 0;
        startDate = "2018-09-03";
        endDate = "2018-09-08";
        entryId = 1;
        homeworkFileId = 1;
        lessonNumber = 1;
        lessonName = "Хімія";
        homework = "ДЗ";
        mark = 9;
        note = "Молодець";
        
        List<Map<String, Object>> diary = new ArrayList<>();
        Map<String, Object> diaryEntry = new HashMap<>();
        diaryEntry.put(ID_KEY, entryId);
        diaryEntry.put(HOMEWORK_FILE_ID_KEY, homeworkFileId);
        diaryEntry.put(DATE_KEY, Date.valueOf(startDate));
        diaryEntry.put(LESSON_NUMBER_KEY, lessonNumber);
        diaryEntry.put(NAME_KEY, lessonName);
        diaryEntry.put(HOMETASK_KEY, homework);
        diaryEntry.put(MARK_KEY, mark);
        diaryEntry.put(NOTE_KEY, note);
        diary.add(diaryEntry);
        
        Mockito.when(lessonRepository.getDiary(studentId, startDate, endDate)).thenReturn(diary);
        
        DiaryEntryDTO expectedDTO = DiaryEntryDTO.builder()
                .lessonId(entryId)
                .date(LocalDate.parse(startDate))
                .homeworkFileId(homeworkFileId)
                .lessonNumber(lessonNumber)
                .subjectName(lessonName)
                .homeWork(homework)
                .mark(mark)
                .note(note)
                .build();
        List<DiaryEntryDTO> result = diaryService.getDiary(LocalDate.parse(startDate), studentId);
        assertEquals("result size", 1, result.size());
        assertEquals("result object", expectedDTO, result.get(0));
    }
    
    
    @Test
    public void serviceShouldCorrectlyHandleNullsInResultSet() {
        studentId = 0;
        startDate = "2018-09-03";
        endDate = "2018-09-08";
        entryId = 1;
        homeworkFileId = null;
        lessonNumber = 1;
        lessonName = "Хімія";
        homework = null;
        String homeworkVal = "";
        mark = null;
        byte markVal = 0;
        note = null;
        String noteVal = "";
        
        List<Map<String, Object>> diary = new ArrayList<>();
        Map<String, Object> diaryEntry = new HashMap<>();
        diaryEntry.put(ID_KEY, entryId);
        diaryEntry.put(HOMEWORK_FILE_ID_KEY, homeworkFileId);
        diaryEntry.put(DATE_KEY, Date.valueOf(startDate));
        diaryEntry.put(LESSON_NUMBER_KEY, lessonNumber);
        diaryEntry.put(NAME_KEY, lessonName);
        diaryEntry.put(HOMETASK_KEY, homework);
        diaryEntry.put(MARK_KEY, mark);
        diaryEntry.put(NOTE_KEY, note);
        diary.add(diaryEntry);
        
        Mockito.when(lessonRepository.getDiary(studentId, startDate, endDate)).thenReturn(diary);
        
        DiaryEntryDTO expectedDTO = DiaryEntryDTO.builder()
                .lessonId(entryId)
                .date(LocalDate.parse(startDate))
                .homeworkFileId(homeworkFileId)
                .lessonNumber(lessonNumber)
                .subjectName(lessonName)
                .homeWork(homeworkVal)
                .mark(markVal)
                .note(noteVal)
                .build();
        List<DiaryEntryDTO> result = diaryService.getDiary(LocalDate.parse(startDate), studentId);
        assertEquals("result size", 1, result.size());
        assertEquals("result object", expectedDTO, result.get(0));
    }
}
