package academy.softserve.eschool.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public abstract class ControllerIntegrationTestBase {
    
    protected static final String ADMIN_CREDENTIALS = "{\"username\":\"admin\", \"password\":\"admin\"}";
    protected static final String TEACHER_CREDENTIALS = "{\"username\":\"aEinst14\", \"password\":\"password\"}";
    protected static final String USER_CREDENTIALS = "{\"username\":\"kPolyan16\", \"password\":\"password\"}";
    protected static final ObjectMapper mapper = new ObjectMapper();
    
    @Value("${jwt.token.header}")
    protected String tokenHeader;
    
    @Autowired
    protected MockMvc mvc;

}
