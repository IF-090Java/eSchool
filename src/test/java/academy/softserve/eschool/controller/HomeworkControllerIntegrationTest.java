package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.HomeworkFileDTO;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import academy.softserve.eschool.service.JournalService;
import static org.hamcrest.Matchers.*;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        mvc.perform(MockMvcRequestBuilders.get("/homeworks/subjects/3/classes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].idLesson").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].date").value("2018.09.04"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].homework").value("Домашнє завдання #15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].fileName").value("test.txt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.*", hasSize(18)));
    }

    @Test
    public void postHomeworksTest() throws Exception {
        HomeworkFileDTO homeworkFileDTO = HomeworkFileDTO.builder()
                .homework("test homework")
                .idLesson(11)
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
