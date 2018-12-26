package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.repository.SubjectRepository;
import academy.softserve.eschool.repository.TeacherRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * This is a test class for {@link ClassTeacherSubjectServiceImpl}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 *
 *  @author Mariana Vorotniak
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassTeacherSubjectServiceImplTest {

    @Mock
    private ClassTeacherSubjectLinkRepository classTeacherSubjectRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private ClassTeacherSubjectServiceImpl classTeacherSubjectService;

    private static TeacherJournalDTO teacherJournalDTO;

    @Before
    public void init()
    {
        teacherJournalDTO = new TeacherJournalDTO(1, 1, 1);
    }

    @Test
    public void saveClassTeacherSubject()
    {
        Optional<Object> clazz = Optional.of(new Clazz(1, "8-А", "",2017, false));
        Optional<Object> subject = Optional.of(new Subject(1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        Mockito.doReturn(clazz).when(classRepository).findById(anyInt());
        Mockito.doReturn(Optional.of(new Teacher())).when(teacherRepository).findById(anyInt());
        Mockito.doReturn(subject).when(subjectRepository).findById(anyInt());
        ClassTeacherSubjectLink classTeacherSubjectLink = new ClassTeacherSubjectLink();
        classTeacherSubjectLink.setClazz(new Clazz(1, "8-А", "",2017, false));
        classTeacherSubjectLink.setClazz_id(1);
        classTeacherSubjectLink.setTeacher(new Teacher());
        classTeacherSubjectLink.setTeacher_id(1);
        classTeacherSubjectLink.setSubject(new Subject(1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        classTeacherSubjectLink.setSubject_id(1);
        classTeacherSubjectLink.setActive(true);
        assertEquals("Saving teacher-journal connection", classTeacherSubjectLink, classTeacherSubjectService.saveClassTeacherSubject(teacherJournalDTO, true));
    }

    @After
    public void destroy()
    {
        teacherJournalDTO = null;
    }
}
