package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Mark;
import academy.softserve.eschool.model.MarkType;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.MarkRepository;
import academy.softserve.eschool.repository.MarkTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MarkServiceTest {
    
    private String AVG_MARK_KEY = "avg_mark";
    private String DATE_KEY = "date";
    private String COUNT_KEY = "count";
    
    private Integer subjectId;
    private Integer classId;
    private Integer studentId;
    private String startDate;
    private String endDate;
    private BigDecimal avg_mark;
    private String date;
    private BigInteger count;
    private MarkDTO markDTO;
    private Mark mark;
    private Student student;
    private Lesson lesson;

    @InjectMocks
    private MarkService markService;
    
    @Mock
    private MarkRepository markRepository;

    @Mock
    private MarkTypeRepository markTypeRepository;
    
    @Before
    public void setUp() {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> dataPoint = new HashMap<>();
        result.add(dataPoint);
        subjectId = 1;
        classId = 2;
        studentId = null;
        startDate = "2018-09-03";
        endDate = "2018-09-04";
        avg_mark = BigDecimal.valueOf(7.65);
        date = "2018-09-03";
        count = BigInteger.valueOf(5);
        
        dataPoint.put(AVG_MARK_KEY, avg_mark);
        dataPoint.put(DATE_KEY, java.sql.Date.valueOf(date));
        dataPoint.put(COUNT_KEY, count);
        
        Mockito.when(markRepository.getFilteredByParamsGroupedByDate(subjectId, classId, studentId, startDate, endDate))
                .thenReturn(result);

        markDTO = MarkDTO.builder()
                .idStudent(1)
                .idLesson(1)
                .note("note")
                .mark((byte)12)
                .build();

        lesson = Lesson.builder()
                .id(1)
                .markType(new MarkType())
                .hometask("testHomeWork")
                .date(java.sql.Date.valueOf(LocalDate.of(2012,2,2)))
                .lessonNumber((byte)3)
                .build();

        student = Student.builder()
                .firstName("Ruslan")
                .lastName("Kharevych")
                .build();
        student.setId(1);

        mark = Mark.builder()
                .id(1)
                .mark((byte)12)
                .note("note")
                .lesson(lesson)
                .student(student)
                .build();
    }
    
    @Test
    public void serviceShouldCorrectlyBindRepositoryResultToDTO() {
        MarkDataPointDTO expectedDataPoint = new MarkDataPointDTO(avg_mark.doubleValue(), LocalDate.parse(date), count.intValue());
        List<MarkDataPointDTO> result = markService.getFilteredByParams(subjectId, classId, studentId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        
        assertEquals("result length", 1, result.size());
        assertEquals("result object", expectedDataPoint, result.get(0));
    }

    @Test
    public void saveMarkTest(){
        Mockito.when(markRepository.findTopByStudentIdAndLessonId(Mockito.anyInt(),Mockito.anyInt())).thenReturn(mark);
        assertEquals("Test markDTO",markDTO.getIdStudent(),markService.saveMark(markDTO).getIdStudent());
        assertEquals("Test markDTO",markDTO.getIdLesson(),markService.saveMark(markDTO).getIdLesson());
        assertEquals("Test markDTO",markDTO.getMark(),markService.saveMark(markDTO).getMark());
        assertEquals("Test markDTO",markDTO.getNote(),markService.saveMark(markDTO).getNote());
    }

    @Test
    public void updateTypeTest(){
        int idLesson = 1;
        String markType = "Module";
        Mockito.when(markTypeRepository.findByMarkType(markType)).thenReturn(MarkType.builder().markType(markType).id(2).build());
        markService.updateType(idLesson,markType);
        Mockito.verify(markRepository,Mockito.times(1)).saveTypeByLesson(idLesson,2);
    }
    
}
