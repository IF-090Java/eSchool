package academy.softserve.eschool.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.*;
import academy.softserve.eschool.repository.*;
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

    private static List<JournalDTO> journalDTOList;

    private static List<Map<String,Object>> listJournalMark;

    private static List<Lesson> listLesson;

    private static Clazz clazz;

    private static Subject subject;

    private static File file;

    private static ClassTeacherSubjectLink link;

    private static Map<String,Object> journalMarks;

    @BeforeClass
    public static void init() {
        listConnections = new ArrayList<>();
        listJournalMark = new ArrayList<>();
        listLesson = new ArrayList<>();
        journalDTOList = new ArrayList<>();

        clazz = Clazz.builder()
                .id(1)
                .name("7-А")
                .description("description")
                .academicYear(2018)
                .isActive(true)
                .build();

        subject = Subject.builder()
                .id(1)
                .description("descr")
                .name("Українська мова")
                .build();

        file = File.builder()
                .fileType("testType")
                .fileName("test.txt")
                .file("testData")
                .build();

        lesson = Lesson.builder()
                .id(1)
                .markType(Mark.MarkType.Module)
                .hometask("testHomeWork")
                .date(java.sql.Date.valueOf(LocalDate.of(2012,2,2)))
                .clazz(clazz)
                .subject(subject)
                .file(file)
                .lessonNumber((byte)3)
                .build();

        link = ClassTeacherSubjectLink.builder()
                .clazz(clazz)
                .teacher(new Teacher())
                .subject(subject)
                .isActive(true)
                .build();

        journalDTO = JournalDTO.builder()
                .academicYear(2018)
                .className("7-А")
                .idClass(1)
                .idSubject(1)
                .subjectName("Українська мова")
                .build();

        journalDTOList.add(journalDTO);
        listConnections.add(link);
        listLesson.add(lesson);

        journalMarks = new HashMap<>();
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
        assertEquals("Test fileDTO", homeworkFileDTO, journalService.getFile(1));
    }

    @Test
    public void getJournalsByTeacherTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findJournalsByTeacher(anyInt())).thenReturn(listConnections);
        assertEquals("Test journalDTOList",journalDTOList,journalService.getJournalsByTeacher(1));
    }

    @Test
    public void getActiveJournalsByTeacherTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findActiveJournalsByTeacher(anyInt())).thenReturn(listConnections);
        assertEquals("Test journalDTOList",journalDTOList,journalService.getActiveJournalsByTeacher(1));
    }

    @Test
    public void getJournalsTest(){
        Mockito.when(classTeacherSubjectLinkRepository.findJournals()).thenReturn(listConnections);
        assertEquals("Test journalDTOList",journalDTOList,journalService.getJournals());
    }

    @Test
    public void getJournalTest(){
        List<MarkDescriptionDTO> desList = new ArrayList<>();

        MarkDescriptionDTO markDescriptionDTO = MarkDescriptionDTO.builder()
                .idLesson(1)
                .mark((byte)12)
                .typeMark("Module")
                .note("note")
                .dateMark(java.sql.Date.valueOf(LocalDate.of(2012,2,2)))
                .build();

        JournalMarkDTO testJMDTO = JournalMarkDTO.builder()
                .idStudent(1)
                .studentFullName("Ruslan Kharevych")
                .marks(desList)
                .build();

        desList.add(markDescriptionDTO);
        List<JournalMarkDTO> journalMarkDTOList = new ArrayList<>();
        journalMarkDTOList.add(testJMDTO);

        Mockito.when(studentRepository.findJournal(anyInt(),anyInt())).thenReturn(listJournalMark);
        assertEquals("Test journalMarkDTOList",journalMarkDTOList,journalService.getJournal(anyInt(),anyInt()));
    }

    @Test
    public void getHomeworkTest(){
        List<HomeworkDTO> homeworkDTOList = new ArrayList<>();

        HomeworkDTO homeworkDTO = HomeworkDTO.builder()
                .idLesson(1)
                .date(java.sql.Date.valueOf(LocalDate.of(2012,2,2)))
                .homework("testHomeWork")
                .fileName("test.txt")
                .build();

        homeworkDTOList.add(homeworkDTO);

        Mockito.when(lessonRepository.findHomework(anyInt(),anyInt())).thenReturn(listLesson);
        assertEquals("Test homeworkDTOList",homeworkDTOList,journalService.getHomework(anyInt(),anyInt()));
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
        subject = null;
        file = null;
        link = null;
        journalMarks = null;
        journalDTOList = null;
    }
}

