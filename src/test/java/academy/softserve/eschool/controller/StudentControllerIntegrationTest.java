package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerIntegrationTest {
    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final TeacherDTO testObj1 = TeacherDTO.builder().firstname("Іван")
            .lastname("Тестовий")
            .patronymic("Тест")
            .login("testlogin")
            .dateOfBirth(LocalDate.ofYearDay(2000, 1)).build();
    private static final EditUserDTO testObj2 = EditUserDTO.builder().firstname("Іван")
            .lastname("Змінений")
            .patronymic("По-бфтькові")
            .login("bGates28")
            .oldPass("password")
            .newPass("qwerty")
            .email("some@mail.com")
            .dateOfBirth(LocalDate.ofYearDay(2000, 2)).build();

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("admin", "admin"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
    }

    @Test
    public void addStudent() {
    }

    @Test
    public void getStudent() {
    }

    @Test
    public void getStudentsByClass() {
    }

    @Test
    public void updateStudent() {
    }
}