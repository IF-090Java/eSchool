package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.LessonDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for {@link ScheduleServiceImpl}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 *
 *  @author Mariana Vorotniak
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private MarkTypeRepository markTypeRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private static List<LessonDTO> list = new ArrayList<>();
    private static LocalDate start, end;
    private static DayOfWeek dayOfWeek;
    private static Clazz clazz;
    private static List<Subject> subjectList = new ArrayList<>();
    private static List<Map<String, Object>> listOfMaps = new ArrayList<>();


    @Before
    public void init()
    {
        subjectList.add(new Subject(1, "Історія України", ""));
        subjectList.add(new Subject(2, "Інформатика", ""));
        subjectList.add(new Subject(8, "Біологія", ""));
        subjectList.add(new Subject(9, "Математика", ""));

        list.add(new LessonDTO((byte) 1, 1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        list.add(new LessonDTO((byte) 2, 1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        list.add(new LessonDTO((byte) 3, 9, "Математика", ""));
        list.add(new LessonDTO((byte) 4, 8, "Біологія", "Природничий навчальний предмет. Починає вивчатись із 6-го класу"));
        list.add(new LessonDTO((byte) 5, 8, "Біологія", "Природничий навчальний предмет. Починає вивчатись із 6-го класу"));

        start = LocalDate.of(2018, Month.DECEMBER, 3);
        end = LocalDate.of(2018, Month.DECEMBER, 16);

        dayOfWeek = DayOfWeek.SATURDAY;

        clazz = new Clazz(2, "7-А", "", 2018, true);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("date", "2018-11-20");
        map1.put("lesson_number", "1");
        map1.put("id", "1");
        map1.put("name", "Історія України");
        map1.put("description", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("date", "2018-12-08");
        map2.put("lesson_number", "1");
        map2.put("id", "2");
        map2.put("name", "Інформатика");
        map2.put("description", "");

        listOfMaps.add(map1);
        listOfMaps.add(map2);
    }

    /**
     * Save schedule test function - checks if all the information was stored.
     * @result Schedule will be created without any errors
     */
    @Test
    public void saveFunctionTest() throws Exception
    {
        Mockito.when(subjectRepository.findAll()).thenReturn(subjectList);
        List<Lesson> lessons = scheduleService.saveFunction(list, start, end, dayOfWeek, clazz);
        assertEquals("Adding schedule for SATURDAY", 10, lessons.size());
    }

    @Test
    public void convertFromObjectTest() throws Exception
    {
        List<LessonDTO> lessonDTOList = new ArrayList<>();
        lessonDTOList.add(new LessonDTO((byte) 1, 2, "Інформатика", ""));
        assertEquals("Getting schedule for a specific class", lessonDTOList, scheduleService.convertFromObject(listOfMaps, dayOfWeek));
    }

    @After
    public void destroy()
    {
        list.clear();
        start = null;
        end = null;
        dayOfWeek = null;
        clazz = null;
        listOfMaps.clear();
    }

}
