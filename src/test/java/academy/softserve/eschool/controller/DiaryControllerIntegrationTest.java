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
public class DiaryControllerIntegrationTest extends ControllerIntegrationTestBase{
    
    private String token;
    private HttpHeaders headers;
    
    @Test
    public void getDiaryTest () throws Exception {
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        String expectedJSON = "{\"status\":{\"code\":200,\"message\":\"OK\"},"
                + "\"data\":[{\"lessonId\":1,\"date\":[2018,9,3],\"lessonNumber\":1,\"subjectName\":\"Математика\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":9,\"note\":\"\"},"
                + "{\"lessonId\":2,\"date\":[2018,9,3],\"lessonNumber\":2,\"subjectName\":\"Хімія\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":3,\"date\":[2018,9,3],\"lessonNumber\":3,\"subjectName\":\"Біологія\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":9,\"note\":\"\"},"
                + "{\"lessonId\":4,\"date\":[2018,9,3],\"lessonNumber\":4,\"subjectName\":\"Фізика\",\"homeWork\":\"Домашнє завдання #47, #92\",\"homeworkFileId\":null,\"mark\":4,\"note\":\"Не готовий!\"},"
                + "{\"lessonId\":5,\"date\":[2018,9,3],\"lessonNumber\":5,\"subjectName\":\"Українська мова\",\"homeWork\":\"Домашнє завдання #9, #4\",\"homeworkFileId\":null,\"mark\":7,\"note\":\"\"},"
                + "{\"lessonId\":6,\"date\":[2018,9,4],\"lessonNumber\":1,\"subjectName\":\"Інформатика\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":4,\"note\":\"Не готовий!\"},"
                + "{\"lessonId\":7,\"date\":[2018,9,4],\"lessonNumber\":2,\"subjectName\":\"Хімія\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":8,\"date\":[2018,9,4],\"lessonNumber\":3,\"subjectName\":\"Українська література\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":9,\"date\":[2018,9,4],\"lessonNumber\":4,\"subjectName\":\"Біологія\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":9,\"note\":\"\"},"
                + "{\"lessonId\":10,\"date\":[2018,9,4],\"lessonNumber\":5,\"subjectName\":\"Біологія\",\"homeWork\":\"Домашнє завдання #18, #84\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":11,\"date\":[2018,9,5],\"lessonNumber\":1,\"subjectName\":\"Англійська мова\",\"homeWork\":\"Домашнє завдання #15\",\"homeworkFileId\":1,\"mark\":4,\"note\":\"Не готовий!\"},"
                + "{\"lessonId\":12,\"date\":[2018,9,5],\"lessonNumber\":2,\"subjectName\":\"Фізика\",\"homeWork\":\"Домашнє завдання #15, #92\",\"homeworkFileId\":null,\"mark\":4,\"note\":\"Не готовий!\"},"
                + "{\"lessonId\":13,\"date\":[2018,9,5],\"lessonNumber\":3,\"subjectName\":\"Українська література\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":14,\"date\":[2018,9,5],\"lessonNumber\":4,\"subjectName\":\"Українська література\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":15,\"date\":[2018,9,6],\"lessonNumber\":1,\"subjectName\":\"Інформатика\",\"homeWork\":\"Домашнє завдання #57, #19\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":16,\"date\":[2018,9,6],\"lessonNumber\":2,\"subjectName\":\"Історія України\",\"homeWork\":\"Домашнє завдання #44, #70\",\"homeworkFileId\":null,\"mark\":6,\"note\":\"\"},"
                + "{\"lessonId\":17,\"date\":[2018,9,6],\"lessonNumber\":3,\"subjectName\":\"Українська мова\",\"homeWork\":\"Домашнє завдання #73, #52\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":18,\"date\":[2018,9,7],\"lessonNumber\":1,\"subjectName\":\"Українська література\",\"homeWork\":\"Домашнє завдання #15, #26\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":19,\"date\":[2018,9,7],\"lessonNumber\":2,\"subjectName\":\"Біологія\",\"homeWork\":\"Домашнє завдання #42, #53\",\"homeworkFileId\":null,\"mark\":0,\"note\":\"\"},"
                + "{\"lessonId\":20,\"date\":[2018,9,7],\"lessonNumber\":3,\"subjectName\":\"Англійська мова\",\"homeWork\":\"Домашнє завдання #67, #95\",\"homeworkFileId\":null,\"mark\":7,\"note\":\"\"},"
                + "{\"lessonId\":21,\"date\":[2018,9,7],\"lessonNumber\":4,\"subjectName\":\"Історія України\",\"homeWork\":\"\",\"homeworkFileId\":null,\"mark\":7,\"note\":\"\"}]}";
        
        mvc.perform(MockMvcRequestBuilders.get("/diaries")
                .headers(headers)
                .param("weekStartDate", "2018-09-03"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false));
    }
    
    @Test
    public void unautorizedUserShouldNotHaveAccesToDiary() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/diaries")
                .param("weekStartDate", "2018-09-03"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    public void teacherSholdNotHaveAccessToDiary() throws Exception{
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TEACHER_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        mvc.perform(MockMvcRequestBuilders.get("/diaries")
                .headers(headers)
                .param("weekStartDate", "2018-09-03"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        
    }
    
    @Test
    public void adminSholdNotHaveAccessToDiary() throws Exception{
        
        mvc.perform(MockMvcRequestBuilders.post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ADMIN_CREDENTIALS))
                .andExpect(status().isNoContent())
                .andDo(mvcResult -> token = mvcResult.getResponse().getHeader(tokenHeader));
        headers = new HttpHeaders();
        headers.add(tokenHeader, token);
        
        mvc.perform(MockMvcRequestBuilders.get("/diaries")
                .headers(headers)
                .param("weekStartDate", "2018-09-03"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        
    }
}
