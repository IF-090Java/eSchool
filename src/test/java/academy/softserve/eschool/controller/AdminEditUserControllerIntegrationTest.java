package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AdminEditUserControllerIntegrationTest {
    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final StudentDTO student = StudentDTO.builder().firstname("Іван")
            .lastname("Тестовий")
            .patronymic("Тест")
            .login("testlogin")
            .dateOfBirth(LocalDate.ofYearDay(2000, 3)).build();
    private static final TeacherDTO teacher = TeacherDTO.builder().firstname("Іван")
            .lastname("Тестовий")
            .patronymic("Тест")
            .login("testlogin")
            .dateOfBirth(LocalDate.ofYearDay(2000, 3)).build();
    private static final EditUserDTO editionForStudent = EditUserDTO.builder().firstname("Іван")
            .lastname("Змінений")
            .patronymic("Змінений")
            .login("bGates")
            .login("updatedLogin")
            .oldPass("password")
            .newPass("qwerty")
            .email("some@mail.com")
            .dateOfBirth(LocalDate.ofYearDay(2000, 2)).build();

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;


    @Before
    public void init() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("admin", "admin"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
    }

    @Test
    public void updateStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.put("/admin/students/9")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(editionForStudent)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Іван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("Змінений"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("updatedLogin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("some@mail.com"));
    }

    @Test
    public void updateTeacher() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.put("/admin/teachers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(editionForStudent)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstname").value("Іван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastname").value("Змінений"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("updatedLogin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("some@mail.com"));
    }
}