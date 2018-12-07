package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import static org.hamcrest.Matchers.*;
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
public class HomeworkControllerIntegrationTest {

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
    public void getHomeworksTest() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},\"data\":[" +
                "{\"idLesson\":11,\"date\":\"2018.09.04\",\"homework\":\"Домашнє завдання #15\",\"fileName\":\"test.txt\"}," +
                "{\"idLesson\":20,\"date\":\"2018.09.06\",\"homework\":\"Домашнє завдання #67, #95\",\"fileName\":null}," +
                "{\"idLesson\":32,\"date\":\"2018.09.11\",\"homework\":\"Домашнє завдання #94, #22\",\"fileName\":null}," +
                "{\"idLesson\":41,\"date\":\"2018.09.13\",\"homework\":\"Домашнє завдання #83\",\"fileName\":null}," +
                "{\"idLesson\":53,\"date\":\"2018.09.18\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":62,\"date\":\"2018.09.20\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":74,\"date\":\"2018.09.25\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":83,\"date\":\"2018.09.27\",\"homework\":\"Домашнє завдання #40, #2\",\"fileName\":null}" +
                ",{\"idLesson\":95,\"date\":\"2018.10.02\",\"homework\":\"Домашнє завдання #85, #72\",\"fileName\":null}," +
                "{\"idLesson\":104,\"date\":\"2018.10.04\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":116,\"date\":\"2018.10.09\",\"homework\":\"Домашнє завдання #73, #83\",\"fileName\":null}," +
                "{\"idLesson\":125,\"date\":\"2018.10.11\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":137,\"date\":\"2018.10.16\",\"homework\":\"Домашнє завдання #82, #10\",\"fileName\":null}," +
                "{\"idLesson\":146,\"date\":\"2018.10.18\",\"homework\":\"Домашнє завдання #21, #35\",\"fileName\":null}," +
                "{\"idLesson\":158,\"date\":\"2018.10.23\",\"homework\":\"Домашнє завдання #27, #89\",\"fileName\":null}," +
                "{\"idLesson\":167,\"date\":\"2018.10.25\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":179,\"date\":\"2018.10.30\",\"homework\":\"\",\"fileName\":null}," +
                "{\"idLesson\":188,\"date\":\"2018.11.01\",\"homework\":\"\",\"fileName\":null}]}";
        mvc.perform(MockMvcRequestBuilders.get("/homeworks/subjects/3/classes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void postHomeworksTest() throws Exception {
        HomeworkFileDTO homeworkFileDTO = HomeworkFileDTO.builder()
                .homework("test homework")
                .idLesson(20)
                .fileData("test data")
                .fileName("file name")
                .fileType("file type")
                .build();

        mvc.perform(MockMvcRequestBuilders.put("/homeworks/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(homeworkFileDTO))
                .headers(headers))
                .andExpect((MockMvcResultMatchers.jsonPath("$.status.code")).value(204));
    }

    @Test
    public void getFileTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/homeworks/files/11")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.idLesson").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.homework").value("Домашнє завдання #15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fileName").value("test.txt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fileType").value("testType"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fileData").value("testData"));
    }
}
