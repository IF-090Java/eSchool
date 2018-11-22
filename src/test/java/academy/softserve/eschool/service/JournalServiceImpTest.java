package academy.softserve.eschool.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.model.*;
import academy.softserve.eschool.repository.*;
import jdk.nashorn.internal.scripts.JO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        assertEquals("Test", homeworkFileDTO, journalService.getFile(1));
    }

    @After
    public void destroy() {
        homeworkFileDTO = null;
        lesson = null;
    }
}

