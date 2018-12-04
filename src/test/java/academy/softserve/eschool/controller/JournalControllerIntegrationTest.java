package academy.softserve.eschool.controller;

import static org.hamcrest.Matchers.hasSize;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
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
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},\"data\":[" +
                "{\"idSubject\":5,\"idClass\":5,\"subjectName\":\"Українська література\",\"className\":\"5-А\",\"academicYear\":2016}," +
                "{\"idSubject\":5,\"idClass\":3,\"subjectName\":\"Українська література\",\"className\":\"7-Б\",\"academicYear\":2018}," +
                "{\"idSubject\":10,\"idClass\":4,\"subjectName\":\"Хімія\",\"className\":\"9\",\"academicYear\":2018}," +
                "{\"idSubject\":9,\"idClass\":5,\"subjectName\":\"Математика\",\"className\":\"5-А\",\"academicYear\":2016}," +
                "{\"idSubject\":4,\"idClass\":1,\"subjectName\":\"Українська мова\",\"className\":\"8-А\",\"academicYear\":2017}," +
                "{\"idSubject\":7,\"idClass\":4,\"subjectName\":\"Географія\",\"className\":\"9\",\"academicYear\":2018}," +
                "{\"idSubject\":4,\"idClass\":3,\"subjectName\":\"Українська мова\",\"className\":\"7-Б\",\"academicYear\":2018}," +
                "{\"idSubject\":2,\"idClass\":1,\"subjectName\":\"Інформатика\",\"className\":\"8-А\",\"academicYear\":2017}," +
                "{\"idSubject\":1,\"idClass\":1,\"subjectName\":\"Історія України\",\"className\":\"8-А\",\"academicYear\":2017}," +
                "{\"idSubject\":8,\"idClass\":3,\"subjectName\":\"Біологія\",\"className\":\"7-Б\",\"academicYear\":2018}," +
                "{\"idSubject\":8,\"idClass\":4,\"subjectName\":\"Біологія\",\"className\":\"9\",\"academicYear\":2018}," +
                "{\"idSubject\":9,\"idClass\":2,\"subjectName\":\"Математика\",\"className\":\"7-А\",\"academicYear\":2018}," +
                "{\"idSubject\":9,\"idClass\":4,\"subjectName\":\"Математика\",\"className\":\"9\",\"academicYear\":2018}," +
                "{\"idSubject\":2,\"idClass\":5,\"subjectName\":\"Інформатика\",\"className\":\"5-А\",\"academicYear\":2016}," +
                "{\"idSubject\":3,\"idClass\":2,\"subjectName\":\"Англійська мова\",\"className\":\"7-А\",\"academicYear\":2018}," +
                "{\"idSubject\":1,\"idClass\":5,\"subjectName\":\"Історія України\",\"className\":\"5-А\",\"academicYear\":2016}," +
                "{\"idSubject\":1,\"idClass\":3,\"subjectName\":\"Історія України\",\"className\":\"7-Б\",\"academicYear\":2018}," +
                "{\"idSubject\":3,\"idClass\":1,\"subjectName\":\"Англійська мова\",\"className\":\"8-А\",\"academicYear\":2017}," +
                "{\"idSubject\":5,\"idClass\":2,\"subjectName\":\"Українська література\",\"className\":\"7-А\",\"academicYear\":2018}," +
                "{\"idSubject\":5,\"idClass\":1,\"subjectName\":\"Українська література\",\"className\":\"8-А\",\"academicYear\":2017}," +
                "{\"idSubject\":6,\"idClass\":2,\"subjectName\":\"Фізика\",\"className\":\"7-А\",\"academicYear\":2018}," +
                "{\"idSubject\":6,\"idClass\":4,\"subjectName\":\"Фізика\",\"className\":\"9\",\"academicYear\":2018}," +
                "{\"idSubject\":10,\"idClass\":3,\"subjectName\":\"Хімія\",\"className\":\"7-Б\",\"academicYear\":2018}," +
                "{\"idSubject\":10,\"idClass\":5,\"subjectName\":\"Хімія\",\"className\":\"5-А\",\"academicYear\":2016}]}";

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
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void getJournalsTeacherTest() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},\"data\":[" +
                "{\"idSubject\":3,\"idClass\":2,\"subjectName\":\"Англійська мова\",\"className\":\"7-А\",\"academicYear\":2018}," +
                "{\"idSubject\":3,\"idClass\":1,\"subjectName\":\"Англійська мова\",\"className\":\"8-А\",\"academicYear\":2017}]}";

        mvc.perform(MockMvcRequestBuilders.get("/journals/teachers/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void getActiveJournalsTeacherTest() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},\"data\":[" +
                "{\"idSubject\":3,\"idClass\":2,\"subjectName\":\"Англійська мова\",\"className\":\"7-А\",\"academicYear\":2018}]}";

        mvc.perform(MockMvcRequestBuilders.get("/journals/teachers/2/active")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idStudent").value(18))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].studentFullName").value("Ірина Грушецька"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].marks").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].idStudent").value(19))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].studentFullName").value("Катерина Полянська"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].marks").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].idStudent").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].studentFullName").value("Марія Василик"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].marks").isNotEmpty());

    }
}
