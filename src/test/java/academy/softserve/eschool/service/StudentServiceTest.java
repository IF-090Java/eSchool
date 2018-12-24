package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.CustomPasswordEncoder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This is a test class for {@link StudentService}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class StudentServiceTest {

    @Mock
    private ClassRepository classRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @Mock
    private LoginGeneratorService generateLogin;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private static List<StudentDTO> studentDTOS = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    @BeforeClass
    public static void init() {
        students.add(new Student("iivanov", "password", "@mail.com", Student.Role.ROLE_USER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        students.add(new Student("iivanov1", "password", "@mail.com", Student.Role.ROLE_USER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        students.add(new Student("afedoriv", "password", "@mail.com", Student.Role.ROLE_USER, "Аліна", "Федорів", "Петрівна", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        students.add(new Student("pshevchuk", "password", "@mail.com", Student.Role.ROLE_USER, "Петро", "Шевчук", "Петрович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));

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
        assertEquals(studentDTOS.get(0), studentService.getOne(students.get(0)));
        assertEquals(studentDTOS.get(1), studentService.getOne(students.get(1)));
        assertNotEquals(studentDTOS.get(1), studentService.getOne(students.get(2)));
    }

    @Test
    public void getAll() {
        assertEquals(studentDTOS, studentService.getAll(students));
    }

    @Test
    public void addOne() {
        Mockito.when(studentRepository.save(students.get(0))).thenReturn(students.get(0));
        assertEquals(students.get(0), studentService.addOne(studentDTOS.get(0)));
    }

    @AfterClass
    public static void destroy() {
        students.clear();
        studentDTOS.clear();
    }
}