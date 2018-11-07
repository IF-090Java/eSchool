package academy.softserve.eschool.security.controller;

import academy.softserve.eschool.security.JwtAuthenticationRequest;
import academy.softserve.eschool.security.JwtTokenUtil;
import academy.softserve.eschool.security.JwtUser;
import academy.softserve.eschool.security.exceptions.TokenGlobalTimeExpiredException;
import academy.softserve.eschool.security.service.JwtAuthenticationResponse;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AuthenticationController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @ApiOperation("Login to site with username and password. Returns token. Not protected")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 200 , message = "Successfully signed in"),
                    @ApiResponse( code = 400, message = "Bad credentials"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public GeneralResponseWrapper<JwtAuthenticationResponse> createAuthenticationToken
            (@ApiParam(value = "Login and Password", required = true) @RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return new GeneralResponseWrapper<> (new Status(HttpStatus.OK.value() , "OK") , new JwtAuthenticationResponse(token));
    }
    @ApiOperation("Refresh token. Requires valid and active token. Returns new token")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 200 , message = "Token refreshed"),
                    @ApiResponse( code = 400, message = "Malformed token"),
                    @ApiResponse( code = 401, message = "Unauthorized"),
                    @ApiResponse( code = 403, message = "Forbidden"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public GeneralResponseWrapper<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) throws TokenGlobalTimeExpiredException {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return new GeneralResponseWrapper<>(new Status(200, "OK"), new JwtAuthenticationResponse(refreshedToken));
        } else {
            throw new TokenGlobalTimeExpiredException("Token global lifetime expired");
        }
    }

//    @ExceptionHandler({AuthenticationException.class})
//    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }

}
