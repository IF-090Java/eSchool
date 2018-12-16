package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
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
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},"
                + "\"data\":{\"id\":0,\"classYear\":2017,\"className\":\"8-А\",\"classDescription\":null,\"isActive\":false,\"numOfStudents\":0}}";
        mvc.perform(MockMvcRequestBuilders.get("/classes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testGetAllClassesBySubject() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 200,\"message\": \"OK\"},\"data\": ["
                + "{\"id\": 1,\"classYear\": 2017,\"className\": \"8-А\",\"classDescription\": null,\"isActive\": false,\"numOfStudents\": 3},"
                + "{\"id\": 5,\"classYear\": 2016,\"className\": \"5-А\",\"classDescription\": null,\"isActive\": false,\"numOfStudents\": 3},"
                + "{\"id\": 3,\"classYear\": 2018,\"className\": \"7-Б\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 3}]}";
        mvc.perform(MockMvcRequestBuilders.get("/classes?subjectId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testGetAllClasses() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 200,\"message\": \"OK\"},\"data\": ["
                + "{\"id\": 1,\"classYear\": 2017,\"className\": \"8-А\",\"classDescription\": null,\"isActive\": false,\"numOfStudents\": 3},"
                + "{\"id\": 2,\"classYear\": 2019,\"className\": \"7-А\",\"classDescription\": \"Updated class\",\"isActive\": true,\"numOfStudents\": 3},"
                + "{\"id\": 3,\"classYear\": 2018,\"className\": \"7-Б\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 3},"
                + "{\"id\": 4,\"classYear\": 2018,\"className\": \"9\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 6},"
                + "{\"id\": 5,\"classYear\": 2016,\"className\": \"5-А\",\"classDescription\": null,\"isActive\": false,\"numOfStudents\": 3},"
                + "{\"id\": 6,\"classYear\": 2019,\"className\": \"7-Б\",\"classDescription\":\"\",\"isActive\": true,\"numOfStudents\": 0}]}";
        mvc.perform(MockMvcRequestBuilders.get("/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testAddClass() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":201,\"message\":\"Created\"},"
                + "\"data\":{\"id\":6,\"classYear\":2019,\"className\":\"7-Б\",\"classDescription\":\"\",\"isActive\":true,\"numOfStudents\":0}}";
        mvc.perform(MockMvcRequestBuilders.post("/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testClass1)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testUpdateClass() throws Exception {
        String expectedJSON = "{\"status\":{\"code\":201,\"message\":\"Created\"},"
                + "\"data\":{\"id\":2,\"classYear\":2019,\"className\":\"7-А\",\"classDescription\":\"Updated class\",\"isActive\":true,\"numOfStudents\":3}}";
        mvc.perform(MockMvcRequestBuilders.put("/classes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testClass2)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
}