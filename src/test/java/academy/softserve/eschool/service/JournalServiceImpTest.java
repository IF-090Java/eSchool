package academy.softserve.eschool.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.dto.JournalDTO;
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
    private JournalServiceImpl journalService;

    private HomeworkFileDTO homeworkFileDTO;

    private Lesson lesson;

    private List<ClassTeacherSubjectLink> listConnections;

    private JournalDTO journalDTO;


    @Before
    public void init() {

        File file = File.builder()
                .fileType("testType")
                .fileName("test.txt")
                .file("testData")
                .build();
        lesson = Lesson.builder()
                .lessonNumber((byte)3)
                .file(file)
                .id(1)
                .hometask("testHomeWork")
                .markType(Mark.MarkType.Module)
                .build();

        Clazz clazz = new Clazz(1,"7-А","description",2018,true);
        Teacher teacher = new Teacher("login","password","email", User.Role.ROLE_TEACHER,"Антон","Вітонюк",
                "patr",LocalDate.of(1980,2,1), User.Sex.male,"52141","avatar","description");
        Subject subject = new Subject(1,"Українська мова","descr");
        ClassTeacherSubjectLink link = new ClassTeacherSubjectLink(clazz,teacher,subject,true);
        listConnections = new ArrayList<>();
        listConnections.add(link);

        journalDTO = new JournalDTO(1,1,"Українська мова","7-А",2018);
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
        assertEquals("Test journalDTO in method getActiveJournalsByTeacher()",listConnections.size(),journalService.getActiveJournalsByTeacher(1).size());
    }

    @Test
    public void getJournalsTEst(){
        Mockito.when(classTeacherSubjectLinkRepository.findJournals()).thenReturn(listConnections);
        assertEquals("Test journalDTO in method getJournals()",journalDTO,journalService.getJournals().get(0));
        assertEquals("getActiveJournalsByTeacher",listConnections.size(),journalService.getJournals().size());
    }

    @After
    public void destroy() {
        homeworkFileDTO = null;
        lesson = null;
    }
}

