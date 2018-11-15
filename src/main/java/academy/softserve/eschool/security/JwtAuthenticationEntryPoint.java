package academy.softserve.eschool.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7060530738989399327L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        if (httpServletRequest.getRequestURI().equals("/"))
            httpServletResponse.sendRedirect("/ui/login");
        else
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
