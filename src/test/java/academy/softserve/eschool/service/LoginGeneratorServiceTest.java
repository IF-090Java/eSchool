package academy.softserve.eschool.service;

import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginGeneratorServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginGeneratorService loginGeneratorService;

    private static List<User> users = new ArrayList<>();

    @BeforeClass
    public static void init(){
        users.add(new User(1, "iivanov",	"password",	"@mail.com", User.Role.ROLE_TEACHER, "Іван",	"Іванов",	"Іванович",	LocalDate.of(2003, Month.DECEMBER, 4), User.Sex.male, "0575651789", null, null));
        users.add(new User(2, "iivanov1",	"password",	"@mail.com", User.Role.ROLE_TEACHER, "Іван",	"Іванов",	"Іванович",	LocalDate.of(2003, Month.DECEMBER, 4), User.Sex.male, "0575651789", null, null));
        users.add(new User(3, "iivanov2",	"password",	"@mail.com", User.Role.ROLE_TEACHER, "Іван",	"Іванов",	"Іванович",	LocalDate.of(2003, Month.DECEMBER, 4), User.Sex.male, "0575651789", null, null));
        users.add(new User(4, "iivanov3",	"password",	"@mail.com", User.Role.ROLE_TEACHER, "Іван",	"Іванов",	"Іванович",	LocalDate.of(2003, Month.DECEMBER, 4), User.Sex.male, "0575651789", null, null));

    }

    @Test
    public void generateLogin() {
        User user = new User(6, "",	"password",	"@mail.com", User.Role.ROLE_TEACHER, "Іван",	"Іванов",	"Іванович",	LocalDate.of(2003, Month.DECEMBER, 4), User.Sex.male, "0575651789", null, null);
        assertEquals("iivanov", loginGeneratorService.generateLogin(user.getFirstName(), user.getLastName()));

        Mockito.when(userRepository.findUsersWithPartOfLogin("iivanov")).thenReturn(users.subList(1, 3));
        assertEquals("iivanov", loginGeneratorService.generateLogin(user.getFirstName(), user.getLastName()));

        Mockito.when(userRepository.findUsersWithPartOfLogin("iivanov")).thenReturn(users.subList(0, 3));
        assertEquals("iivanov3", loginGeneratorService.generateLogin(user.getFirstName(), user.getLastName()));

        Mockito.when(userRepository.findUsersWithPartOfLogin("iivanov")).thenReturn(Arrays.asList(users.get(0), users.get(2)));
        assertEquals("iivanov1", loginGeneratorService.generateLogin(user.getFirstName(), user.getLastName()));

        Mockito.when(userRepository.findUsersWithPartOfLogin("iivanov")).thenReturn(Arrays.asList(users.get(0), users.get(1), users.get(3)));
        assertEquals("iivanov2", loginGeneratorService.generateLogin(user.getFirstName(), user.getLastName()));
    }
}