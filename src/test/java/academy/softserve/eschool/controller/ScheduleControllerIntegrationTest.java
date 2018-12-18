package academy.softserve.eschool.controller;

import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link ScheduleController}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ScheduleControllerIntegrationTest {

    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;

    private HttpHeaders headers;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    String expectedJSON = "{\"startOfSemester\":\"2019-09-01\",\"endOfSemester\":\"2019-12-01\"," +
            "\"className\":" +
                "{\"id\":2,\"classYear\":2018,\"className\":\"7-А\",\"classDescription\":\"\",\"isActive\":true,\"numOfStudents\":3}," +
            "\"mondaySubjects\":" +
                "[{\"subjectId\":1,\"subjectName\":\"Історія України\",\"subjectDescription\":\"Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу\"}," +
                "{\"subjectId\":2,\"subjectName\":\"Інформатика\",\"subjectDescription\":\"\"}," +
                "{\"subjectId\":2,\"subjectName\":\"Інформатика\",\"subjectDescription\":\"\"}]," +
            "\"tuesdaySubjects\":" +
                "[{\"subjectId\":3,\"subjectName\":\"Англійська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}," +
                "{\"subjectId\":3,\"subjectName\":\"Англійська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}," +
                "{\"subjectId\":3,\"subjectName\":\"Англійська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}]," +
            "\"wednesdaySubjects\":" +
                "[{\"subjectId\":4,\"subjectName\":\"Українська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}," +
                "{\"subjectId\":3,\"subjectName\":\"Англійська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}]," +
            "\"thursdaySubjects\":" +
                "[{\"subjectId\":4,\"subjectName\":\"Українська мова\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}," +
                "{\"subjectId\":5,\"subjectName\":\"Українська література\",\"subjectDescription\":\"Гуманітарний навчальний предмет\"}]," +
            "\"fridaySubjects\":" +
                "[{\"subjectId\":8,\"subjectName\":\"Біологія\",\"subjectDescription\":\"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"}," +
                "{\"subjectId\":9,\"subjectName\":\"Математика\",\"subjectDescription\":\"\"}," +
                "{\"subjectId\":9,\"subjectName\":\"Математика\",\"subjectDescription\":\"\"}]," +
            "\"saturdaySubjects\":" +
                "[{\"subjectId\":8,\"subjectName\":\"Біологія\",\"subjectDescription\":\"Природничий навчальний предмет. Починає вивчатись із 6-го класу\"}," +
                "{\"subjectId\":9,\"subjectName\":\"Математика\",\"subjectDescription\":\"\"}," +
                "{\"subjectId\":9,\"subjectName\":\"Математика\",\"subjectDescription\":\"\"}]," +
            "\"classId\":2}";

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
    public void postScheduleTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/classes/2/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.className")).isNotEmpty())
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.startOfSemester")).value("2019-09-01"));
    }

}
