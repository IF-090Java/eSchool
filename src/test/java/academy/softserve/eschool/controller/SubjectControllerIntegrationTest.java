package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SubjectControllerIntegrationTest {
	
    private String token;
    
    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;
    private static final ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mvc;
    
    private static final SubjectDTO firstTestSubject = SubjectDTO.builder()
            .subjectName("Креслення")
            .subjectDescription("")
            .build();
    
    private static final SubjectDTO secondTestSubject = SubjectDTO.builder()
            .subjectName("Трудове навчання")
            .subjectDescription("Викладається із 5-го класу")
            .build();

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
    @Ignore
    public void testGetAllSubjects() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 200,\"message\": \"OK\"},\"data\": ["
                + "{\"subjectId\": 1,\"subjectName\": \"Історія України\",\"subjectDescription\": \"Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу\"},"
                + "{\"subjectId\": 2,\"subjectName\": \"Інформатика\",\"subjectDescription\": null},"
                + "{\"subjectId\": 3,\"subjectName\": \"Англійська мова\",\"subjectDescription\": \"Гуманітарний навчальний предмет\"},"
                + "{\"subjectId\": 4,\"subjectName\": \"Українська мова\",\"subjectDescription\": \"Гуманітарний навчальний предмет\"},"
                + "{\"subjectId\": 5,\"subjectName\": \"Українська література\",\"subjectDescription\": \"Гуманітарний навчальний предмет\"},"
                + "{\"subjectId\": 6,\"subjectName\": \"Фізика\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 7-го класу\"}"
                + "{\"subjectId\": 7,\"subjectName\": \"Географія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"}"
                + "{\"subjectId\": 8,\"subjectName\": \"Біологія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"}"
                + "{\"subjectId\": 9,\"subjectName\": \"Математика\",\"subjectDescription\": null}"
                + "{\"subjectId\": 10,\"subjectName\": \"Хімія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 7-го класу\"}"
                + "{\"subjectId\": 11,\"subjectName\": \"Креслення\",\"subjectDescription\": \"\"}]}";
        mvc.perform(MockMvcRequestBuilders.get("/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    @Ignore
    public void testGetAllSubjectsByClasses() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 200,\"message\": \"OK\"},\"data\": ["
                + "{\"subjectId\": 6,\"subjectName\": \"Фізика\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 7-го класу\"},"
                + "{\"subjectId\": 7,\"subjectName\": \"Географія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"},"
                + "{\"subjectId\": 8,\"subjectName\": \"Біологія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"},"
                + "{\"subjectId\": 10,\"subjectName\": \"Хімія\",\"subjectDescription\": \"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"},"
                + "{\"subjectId\": 9,\"subjectName\": \"Математика\",\"subjectDescription\": null}]}";
        mvc.perform(MockMvcRequestBuilders.get("/subjects?classId=4")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
    
    @Test
    public void testGetSubjectById() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},"
                + "\"data\":{\"subjectId\": 0,\"subjectName\": \"Історія України\",\"subjectDescription\": \"Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу\"}}";
        mvc.perform(MockMvcRequestBuilders.get("/subjects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    @Ignore
    public void testGetSubjectsTeacher() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 200,\"message\": \"OK\"},\"data\": ["
                + "{\"subjectId\": 4,\"subjectName\": \"Українська мова\",\"subjectDescription\": \"Гуманітарний навчальний предмет\"},"
                + "{\"subjectId\": 5,\"subjectName\": \"Українська література\",\"subjectDescription\": \"Гуманітарний навчальний предмет\"}]}";
        mvc.perform(MockMvcRequestBuilders.get("/subjects/teachers/3")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
    
    @Test
    public void testAddSubject() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":201,\"message\":\"Created\"},"
                + "\"data\":{\"subjectId\": 11,\"subjectName\": \"Креслення\",\"subjectDescription\": \"\"}}";
        mvc.perform(MockMvcRequestBuilders.post("/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(firstTestSubject)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testEditSubject() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":201,\"message\":\"Created\"},"
                + "\"data\":{\"subjectId\": 11,\"subjectName\": \"Трудове навчання\",\"subjectDescription\": \"Викладається із 5-го класу\"}}";
        mvc.perform(MockMvcRequestBuilders.put("/subjects/11")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(secondTestSubject)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
}
