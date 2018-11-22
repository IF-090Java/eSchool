package academy.softserve.eschool.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.repository.MarkRepository;

@RunWith(SpringRunner.class)
public class MarkServiceTest {
    
    private static String AVG_MARK_KEY = "avg_mark";
    private static String DATE_KEY = "date";
    private static String COUNT_KEY = "count";
    
    private Integer subjectId;
    private Integer classId;
    private Integer studentId;
    private String startDate;
    private String endDate;
    private BigDecimal avg_mark;
    private String date;
    private BigInteger count; 
    
    @InjectMocks
    private MarkService markService;
    
    @Mock
    private MarkRepository markRepository;
    
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
    }
    
    @Test
    public void serviceShouldCorrectlyBindRepositoryResultToDTO() {
        MarkDataPointDTO expectedDataPoint = new MarkDataPointDTO(avg_mark.doubleValue(), LocalDate.parse(date), count.intValue());
        List<MarkDataPointDTO> result = markService.getFilteredByParams(subjectId, classId, studentId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        
        assertEquals(1, result.size());
        assertEquals(expectedDataPoint, result.get(0));
    }

}
