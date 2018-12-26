package academy.softserve.eschool.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class StatisticsIntegrationTest extends ControllerIntegrationTestBase {

    private String token;
    private HttpHeaders headers;
    
    @Test
    public void getMarksByParamsTest() throws Exception{
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TEACHER_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},"
                + "\"data\":[{\"y\":5.0,\"x\":[2018,9,17],\"weight\":1},"
                + "{\"y\":12.0,\"x\":[2018,10,1],\"weight\":1}]}";
        
        mvc.perform(MockMvcRequestBuilders.get("/marks")
                .headers(headers)
                .param("subject_id", "9")
                .param("student_id", "20")
                .param("class_id", "2")
                .param("period_start", "2018-09-03")
                .param("period_end", "2018-10-04"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
    
    @Test
    public void userShoudlNotHaveAccessToStatistics() throws Exception{
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        mvc.perform(MockMvcRequestBuilders.get("/marks")
                .headers(headers)
                .param("subject_id", "9")
                .param("student_id", "20")
                .param("class_id", "2")
                .param("period_start", "2018-09-03")
                .param("period_end", "2018-10-04"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    public void adminShoudlNotHaveAccessToStatistics() throws Exception{
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ADMIN_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        mvc.perform(MockMvcRequestBuilders.get("/marks")
                .headers(headers)
                .param("subject_id", "9")
                .param("student_id", "20")
                .param("class_id", "2")
                .param("period_start", "2018-09-03")
                .param("period_end", "2018-10-04"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void unautorizedUserShoudlNotHaveAccessToStatistics() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/marks")
                .param("subject_id", "9")
                .param("student_id", "20")
                .param("class_id", "2")
                .param("period_start", "2018-09-03")
                .param("period_end", "2018-10-04"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
