package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.NYTransitionDTO;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class NYTransitionControllerIntegrationTest {
    private String token;
    @Value("${jwt.token.header}")
    private String tokenHeader;
    private HttpHeaders headers;
    private static final ObjectMapper mapper = new ObjectMapper();
    private List<NYTransitionDTO> testList1 = new ArrayList<>();
    private static final NYTransitionDTO testObj1 = NYTransitionDTO.builder()
            .oldClassId(1)
            .newClassId(2).build();

    @Autowired
    MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        testList1.add(testObj1);
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
    public void testAddNewYearClasses() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 201,\"message\": \"Created\"},\"data\": ["
                + "{\"id\": 6,\"classYear\": 2019,\"className\": \"8-А\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 0},"
                + "{\"id\": 7,\"classYear\": 2019,\"className\": \"8-Б\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 0},"
                + "{\"id\": 8,\"classYear\": 2019,\"className\": \"10\",\"classDescription\": null,\"isActive\": true,\"numOfStudents\": 0}]}";
        mvc.perform(MockMvcRequestBuilders.post("/students/transition")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }

    @Test
    public void testBindingStudentsToNewClasses() throws Exception {
        String expectedJSON = "{\"status\":{\"code\": 201,\"message\": \"Created\"},\"data\": ["
                + "{\"newClassId\": 2, \"oldClassId\": 1}]}";
        mvc.perform(MockMvcRequestBuilders.put("/students/transition")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(mapper.writeValueAsString(testList1)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
}