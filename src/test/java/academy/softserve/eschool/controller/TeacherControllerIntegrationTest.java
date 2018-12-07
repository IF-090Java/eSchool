package academy.softserve.eschool.controller;

import static org.junit.Assert.assertEquals;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.User;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TeacherControllerIntegrationTest {
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
    public void getOneByIdTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/teachers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstname").value("Михайло"));
    }

    @Test
    public void getAllTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].firstname").value("Григорій"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].dateOfBirth").value("1971-10-23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].login").value("gBublik23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].lastname").value("Кримський"));
    }

    @Test
    public void addOne() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testObj1)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstname").value("Іван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").isNotEmpty());
    }

    @Test
    public void getRecentlyCreatedByLogin(){
        User user = userRepository.findByLogin(testObj1.getLogin());
        assertEquals(user.getFirstName(),testObj1.getFirstname());
        assertEquals(user.getLastName(),testObj1.getLastname());
        assertEquals(user.getDateOfBirth(), testObj1.getDateOfBirth());
    }

    @Test
    public void update1Test() throws Exception{
        mapper.registerModule(new JavaTimeModule());
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("bGates28", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);
        mvc.perform(MockMvcRequestBuilders.put("/teachers/4")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testObj2)))
                .andExpect(status().isOk());
    }

    @Test
    public void update2Test() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest(testObj2.getLogin(), testObj2.getNewPass()))))
                .andExpect(status().isNoContent())
                .andExpect(header().exists(tokenHeader));
        mvc.perform(MockMvcRequestBuilders.get("/teachers/4").
                contentType(MediaType.APPLICATION_JSON).
                headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.dateOfBirth").value(testObj2.getDateOfBirth().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(testObj2.getEmail()));
    }
}
