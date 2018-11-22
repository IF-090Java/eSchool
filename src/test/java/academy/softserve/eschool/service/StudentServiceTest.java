package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.repository.UserRepository;
import lombok.NonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for {@link StudentService}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    private ClassRepository classRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bcryptEncoder;

    @Mock
    private LoginGeneratorService generateLogin;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private List<StudentDTO> studentDTOS = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    @Before
    public void init() {
        students.add(new Student("iivanov", "password", "@mail.com", Student.Role.ROLE_USER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, ""));
        students.add(new Student("iivanov1", "password", "@mail.com", Student.Role.ROLE_USER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, ""));
        students.add(new Student("afedoriv", "password", "@mail.com", Student.Role.ROLE_USER, "Аліна", "Федорів", "Петрівна", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, ""));
        students.add(new Student("pshevchuk", "password", "@mail.com", Student.Role.ROLE_USER, "Петро", "Шевчук", "Петрович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, ""));

        studentDTOS.add(new StudentDTO("Іван", "Іванов", "Іванович", "7-А", 1,  LocalDate.of(2003, Month.DECEMBER, 4), "iivanov", "@mail.com", "0995456198", null));
        studentDTOS.add(new StudentDTO("Іван", "Іванов", "Іванович", "7-А", 1,  LocalDate.of(2003, Month.DECEMBER, 4), "iivanov1", "@mail.com", "0995456198", null));
        studentDTOS.add(new StudentDTO("Аліна", "Федорів", "Петрівна", "7-А", 1,  LocalDate.of(2003, Month.DECEMBER, 4), "afedoriv", "@mail.com", "0995456198", null));
        studentDTOS.add(new StudentDTO("Петро", "Шевчук", "Петрович", "7-А", 1,  LocalDate.of(2003, Month.DECEMBER, 4), "pshevchuk", "@mail.com", "0995456198", null));

        Clazz clazz = new Clazz(1, "7-А", "", 2018, true);

        for (Student student: students) {
            student.getClasses().add(clazz);
        }
    }

    @Test
    public void getOne() {
        Mockito.when(studentRepository.getOne(1)).thenReturn(students.get(0));
        assertEquals(studentDTOS.get(0), studentService.getOne(students.get(0)));
    }

    @Test
    public void getAll() {
    }

    @After
    public void destroy() {
        students.clear();
        studentDTOS.clear();
    }
}