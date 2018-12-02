package academy.softserve.eschool.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import academy.softserve.eschool.service.JournalService;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.service.MarkService;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JournalControllerIntegrationTest {

    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;

    private HttpHeaders headers;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("gBublik23", "password"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
    }

    @Test
    public void getJournalsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new JwtAuthenticationRequest("admin", "admin"))))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers.clear();
        headers.add(tokenHeader, token);

        mvc.perform(MockMvcRequestBuilders.get("/journals")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idSubject").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idClass").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].subjectName").value("Українська література"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].className").value("5-А"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].academicYear").value("2016"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(24)));
    }

    @Test
    public void getJournalsTeacherTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/journals/teachers/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idSubject").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idClass").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].subjectName").value("Англійська мова"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].className").value("7-А"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].academicYear").value("2018"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(2)));
    }

    @Test
    public void getActiveJournalsTeacherTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/journals/teachers/2/active")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idSubject").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idClass").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].subjectName").value("Англійська мова"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].className").value("7-А"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].academicYear").value("2018"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(1)));
    }

    @Test
    public void getJournalTableTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/journals/subjects/3/classes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(3)));

    }
}
