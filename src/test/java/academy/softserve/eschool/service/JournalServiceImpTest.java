package academy.softserve.eschool.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.*;
import academy.softserve.eschool.repository.*;
import jdk.nashorn.internal.scripts.JO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

/**
 * This is a test class for {@link ScheduleServiceImpl}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 *
 *  @author Ruslan Kharevych
 */
@RunWith(MockitoJUnitRunner.class)
public class JournalServiceImpTest {

    @Mock
    private ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private FileRepository fileRepository;
    @InjectMocks
    private static JournalServiceImpl journalService;

    private static HomeworkFileDTO homeworkFileDTO;

    private static Lesson lesson;

    private static List<ClassTeacherSubjectLink> listConnections;

    private static JournalDTO journalDTO;

    private static List<Map<String,Object>> listJournalMark;

    private static List<Lesson> listLesson;

    private static Clazz clazz;

    private static Teacher teacher;

    private static Subject subject;

    private static File file;

    private static ClassTeacherSubjectLink link;



    @BeforeClass
    public static void init() {
        listConnections = new ArrayList<>();
        listJournalMark = new ArrayList<>();
        listLesson = new ArrayList<>();

        clazz = new Clazz(1,"7-А","description",2018,true);
        teacher = new Teacher("login","password","email", User.Role.ROLE_TEACHER,"Антон","Вітонюк", "patr",LocalDate.of(1980,2,1), User.Sex.male,"52141","avatar","description");
        subject = new Subject(1,"Українська мова","descr");

        file = File.builder().fileType("testType").fileName("test.txt").file("testData").build();
        lesson = new Lesson(1,(byte)3,java.sql.Date.valueOf(LocalDate.of(2012,2,2)),"testHomeWork",Mark.MarkType.Module,file,clazz,subject);

        link = new ClassTeacherSubjectLink(clazz,teacher,subject,true);
        listConnections.add(link);
        journalDTO = new JournalDTO(1,1,"Українська мова","7-А",2018);

        Map<String,Object> journalMarks = new HashMap<>();
        journalMarks.put("first_name","Ruslan");
        journalMarks.put("last_name","Kharevych");
        journalMarks.put("id_student",1);
        journalMarks.put("last_name","Kharevych");
        journalMarks.put("id",1);
        journalMarks.put("mark",(byte)12);
        journalMarks.put("date",java.sql.Date.valueOf(LocalDate.of(2012,2,2)));
        journalMarks.put("mark_type", "Module");
        journalMarks.put("note","note");
        listJournalMark.add(journalMarks);

        listLesson.add(lesson);
    }


    @Test
    public void getFileTest(){
        HomeworkFileDTO homeworkFileDTO = HomeworkFileDTO.builder()
                .idLesson(1)
                .fileData("testData")
                .fileName("test.txt")
                .fileType("testType")
                .homework("testHomeWork")
                .build();
        Mockito.when(lessonRepository.findFile(anyInt())).thenReturn(lesson);
        assertEquals("Test fileDTO int method getFile()", homeworkFileDTO, journalService.getFile(1));
    }

    @Test
    public void getJournalsByTeacherTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findJournalsByTeacher(anyInt())).thenReturn(listConnections);
        assertEquals("Test journalDTO in method getJournalsByTeacher()",journalDTO,journalService.getJournalsByTeacher(1).get(0));
        assertEquals("Test list size in method getJournalsByTeacher()",listConnections.size(),journalService.getJournalsByTeacher(1).size());
    }

    @Test
    public void getActiveJournalsByTeacherTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findActiveJournalsByTeacher(anyInt())).thenReturn(listConnections);
        assertEquals("Test journalDTO in method getActiveJournalsByTeacher()",journalDTO,journalService.getActiveJournalsByTeacher(1).get(0));
        assertEquals("Test list size in method getActiveJournalsByTeacher()",listConnections.size(),journalService.getActiveJournalsByTeacher(1).size());
    }

    @Test
    public void getJournalsTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findJournals()).thenReturn(listConnections);
        assertEquals("Test journalDTO in method getJournals()",journalDTO,journalService.getJournals().get(0));
        assertEquals("Test list size in method getJournals()",listConnections.size(),journalService.getJournals().size());
    }

    @Test
    public void getJournalTest(){
        List<MarkDescriptionDTO> desList = new ArrayList<>();
        desList.add(new MarkDescriptionDTO(1,(byte)12,java.sql.Date.valueOf(LocalDate.of(2012,2,2)),"Module","note"));
        JournalMarkDTO testJMDTO = new JournalMarkDTO(1,"Ruslan Kharevych",desList);
        List<JournalMarkDTO> journalMarkDTO = new ArrayList<>();
        journalMarkDTO.add(testJMDTO);

        Mockito.when(studentRepository.findJournal(anyInt(),anyInt())).thenReturn(listJournalMark);
        assertEquals("Test journalMarkDTO in method getJournals()",journalMarkDTO,journalService.getJournal(anyInt(),anyInt()));
        assertEquals("Test list size in method getActiveJournalsByTeacher()",listJournalMark.size(),journalService.getJournal(anyInt(),anyInt()).size());
    }

    @Test
    public void getHomeworkTest(){
        List<HomeworkDTO> homeworkDTOS = new ArrayList<>();
        homeworkDTOS.add(new HomeworkDTO(1,java.sql.Date.valueOf(LocalDate.of(2012,2,2)),"testHomeWork","test.txt"));

        Mockito.when(lessonRepository.findHomework(anyInt(),anyInt())).thenReturn(listLesson);
        assertEquals("Test journalMarkDTO in method getHomework()",homeworkDTOS,journalService.getHomework(anyInt(),anyInt()));
        assertEquals("Test list size in method getHomework()",listLesson.size(),journalService.getHomework(anyInt(),anyInt()).size());
    }

    @AfterClass
    public static void destroy() {
        homeworkFileDTO = null;
        lesson = null;
        listConnections = null;
        journalDTO = null;
        listJournalMark = null;
        listLesson = null;
        listLesson = null;
        clazz = null;
        teacher = null;
        subject = null;
        file = null;
        link = null;
    }
}

