package academy.softserve.eschool.security;

import academy.softserve.eschool.model.User;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import org.assertj.core.util.DateUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenUtilTest {

    private static final String TEST_USERNAME = "testUsername";
    private static final User.Role TEST_ROLE = User.Role.ROLE_TEACHER;
    private JwtUser jwtUser;

    @Mock
    private JwtUserFactory jwtUserFactory;

    @InjectMocks
    JwtTokenUtil jwtTokenUtil;

    @Mock
    private Clock clockMock;

    @Before
    public void init() {
        jwtUser = new JwtUser(1L, TEST_USERNAME, null, null, null,true, null,
                ReflectionTestUtils.invokeMethod(jwtUserFactory, "mapToGrantedAuthorities", TEST_ROLE));
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L);
        ReflectionTestUtils.setField(jwtTokenUtil, "globalExpiration", 86400L);
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "AZ0cUxm0R8");
    }

    @Test
    public void getUsernameFromToken() {
        when(clockMock.now()).thenReturn(DateUtil.now());
        final String token = createTestToken();
        assertEquals(TEST_USERNAME, jwtTokenUtil.getUsernameFromToken(token));
    }

    @Test
    public void getExpirationDateOfToken() {
        final Date now = DateUtil.now();
        when(clockMock.now()).thenReturn(now);
        final String token = createTestToken();
        final Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
        assertEquals(DateUtil.timeDifference(expirationDateFromToken, now), 3600000L, 2000L);
    }

    @Test
    public void getCreatedDateOfToken() throws Exception {
        final Date now = DateUtil.now();
        when(clockMock.now()).thenReturn(now);
        final String token = createTestToken();
        assertEquals(now.getTime(), jwtTokenUtil.getIssuedAtDateFromToken(token).getTime(), 3000L);
    }

    @Test(expected = ExpiredJwtException.class)
    public void expiredTokenCannotBeRefreshed() {
        when(clockMock.now())
                .thenReturn(DateUtil.yesterday());
        String token = createTestToken();
        jwtTokenUtil.canTokenBeRefreshed(token);
    }

    @Test
    public void notExpiredCanBeRefreshed() {
        when(clockMock.now())
                .thenReturn(DateUtil.now());
        String token = jwtTokenUtil.generateToken(jwtUser);
        assertTrue(jwtTokenUtil.canTokenBeRefreshed(token));
    }

    @Test
    public void testGeneratingTokensForDifferentCreationDates() {
        when(clockMock.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now());
        final String token = createTestToken();
        final String laterToken = createTestToken();
        assertNotEquals(token, laterToken);
    }

    @Test
    public void TokenRefreshTest() {
        when(clockMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.tomorrow());
        String firstToken = createTestToken();
        String refreshedToken = jwtTokenUtil.refreshToken(firstToken);
        Date firstTokenDate = jwtTokenUtil.getExpirationDateFromToken(firstToken);
        Date refreshedTokenDate = jwtTokenUtil.getExpirationDateFromToken(refreshedToken);
        assertTrue(firstTokenDate.before(refreshedTokenDate));
    }

    @Test
    public void TokenValidationTest() {
        when(clockMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.now());
        String token = createTestToken();
        assertTrue(jwtTokenUtil.validateToken(token, jwtUser));
    }

    @After
    public void destroy() {
        jwtUser = null;
        jwtTokenUtil = null;
        jwtUserFactory = null;
        clockMock = null;
    }

    private String createTestToken() {
        return jwtTokenUtil.generateToken(jwtUser);
    }
}
