package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
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
public class ClassControllerIntegrationTest {
    private String token;
    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ClassDTO testClass1 = ClassDTO.builder()
            .className("7-Б")
            .classYear(2019)
            .classDescription("")
            .isActive(true).build();
    private static final ClassDTO testClass2 = ClassDTO.builder()
            .className("7-А")
            .classYear(2019)
            .classDescription("Updated class")
            .isActive(true).build();
    @Autowired
    MockMvc mvc;

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
    public void testGetClassById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/classes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.className").value("8-А"));
    }

    @Test
    public void testGetAllClassesBySubject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/classes?subjectId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].className").value("8-А"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].className").value("5-А"));
    }

    @Test
    public void testGetAllClasses() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].className").value("8-А"));
    }

    @Test
    public void testAddClass() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testClass1)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.className").value("7-Б"));
    }

    @Test
    public void testUpdateClass() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/classes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testClass2)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.classYear").value(2019))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.classDescription").value("Updated class"));
    }
}