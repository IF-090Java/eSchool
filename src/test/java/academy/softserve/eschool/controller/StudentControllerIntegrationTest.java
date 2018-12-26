package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.StudentDTO;
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
public class StudentControllerIntegrationTest {
    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final StudentDTO student = StudentDTO.builder().firstname("Іван")
            .lastname("Тестовий")
            .patronymic("Тест")
            .login("testlogin")
            .classId(1)
            .classe("7-А")
            .dateOfBirth(LocalDate.ofYearDay(2000, 3)).build();
    private static final EditUserDTO editionForStudent = EditUserDTO.builder().firstname("Іван")
            .lastname("Змінений")
            .patronymic("По-бфтькові")
            .login("bGates")
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
    public void addStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Іван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("testlogin"));
    }

    /**
     * Checking access to /students/{id}. Authorized as user with id 9.
     * @throws Exception
     */
    @Test
    public void checkAccessToGetStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("vKrupa01", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.get("/students/9")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/students/10")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().is(403));
    }

    @Test
    public void getStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("vKrupa01", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.get("/students/9")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstname").value("Василь"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastname").value("Крупа"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("vKrupa01"));
    }

    /**
     * Authorized as teacher.
     * @throws Exception
     */
    @Test
    public void getStudentsByClass() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("mGrush03", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.get("/students/classes/5")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());
    }

    @Test
    public void checkAccessToUpdateStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.put("/students/10")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(editionForStudent)))
                .andExpect(status().is(403));
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("vKrupa01", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.put("/students/10")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(editionForStudent)))
                .andExpect(status().is(403));

    }

    @Test
    public void updateStudent() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("vKrupa01", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.put("/students/9")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(editionForStudent)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Василь"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("Крупа"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("some@mail.com"));

    }
}