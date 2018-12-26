package academy.softserve.eschool.service;


import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.TeacherRepository;
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
 * This is a test class for {@link TeacherService}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class TeacherServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @Mock
    private LoginGeneratorService generateLogin;

    @InjectMocks
    private TeacherService teacherService;

    private static List<TeacherDTO> teacherDTOS = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();



    @BeforeClass
    public static void init() {
        teachers.add(new Teacher("iivanov", "password", "@mail.com", Student.Role.ROLE_TEACHER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        teachers.add(new Teacher("iivanov1", "password", "@mail.com", Student.Role.ROLE_TEACHER, "Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        teachers.add(new Teacher("afedoriv", "password", "@mail.com", Student.Role.ROLE_TEACHER, "Аліна", "Федорів", "Петрівна", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));
        teachers.add(new Teacher("pshevchuk", "password", "@mail.com", Student.Role.ROLE_TEACHER, "Петро", "Шевчук", "Петрович", LocalDate.of(2003, Month.DECEMBER, 4), null, "0995456198", null, "", true));

        teacherDTOS.add(new TeacherDTO("Іван", "Іванов", "Іванович",   LocalDate.of(2003, Month.DECEMBER, 4), "iivanov", "@mail.com", "0995456198", null));
        teacherDTOS.add(new TeacherDTO("Іван", "Іванов", "Іванович", LocalDate.of(2003, Month.DECEMBER, 4), "iivanov1", "@mail.com", "0995456198", null));
        teacherDTOS.add(new TeacherDTO("Аліна", "Федорів", "Петрівна",   LocalDate.of(2003, Month.DECEMBER, 4), "afedoriv", "@mail.com", "0995456198", null));
        teacherDTOS.add(new TeacherDTO("Петро", "Шевчук", "Петрович",   LocalDate.of(2003, Month.DECEMBER, 4), "pshevchuk", "@mail.com", "0995456198", null));
    }

    @Test
    public void getOne() {
        assertEquals(teacherDTOS.get(0), teacherService.getOne(teachers.get(0)));
        assertEquals(teacherDTOS.get(1), teacherService.getOne(teachers.get(1)));
        assertNotEquals(teacherDTOS.get(1), teacherService.getOne(teachers.get(2)));
    }

    @Test
    public void getAll() {
        assertEquals(teacherDTOS, teacherService.getAll(teachers));
    }

    @Test
    public void addOne() {
    }

    @AfterClass
    public static void destroy() {
        teachers.clear();
        teacherDTOS.clear();
    }
}