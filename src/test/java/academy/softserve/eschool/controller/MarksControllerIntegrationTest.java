package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.security.JwtAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class MarksControllerIntegrationTest {

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
    public void postHomeworksTest() throws Exception {
        MarkDTO markDTO = MarkDTO.builder()
                .mark((byte)12)
                .note("test")
                .idLesson(11)
                .idStudent(10)
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/marks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(markDTO))
                .headers(headers))
                .andExpect((MockMvcResultMatchers.jsonPath("$.status.code")).value(201))
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.idMark")).isNotEmpty())
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.note")).value("test"))
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.mark")).value(12))
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.idStudent")).value(10))
                .andExpect((MockMvcResultMatchers.jsonPath("$.data.idLesson")).value(11));
    }

    @Test
    public void editTypeTest() throws Exception {
        MarkTypeDTO markTypeDTO = MarkTypeDTO.builder()
                .markType("Модуль")
                .build();

        mvc.perform(MockMvcRequestBuilders.put("/marks/lessons/11/marktype")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(markTypeDTO))
                .headers(headers))
                ;

    }

}
