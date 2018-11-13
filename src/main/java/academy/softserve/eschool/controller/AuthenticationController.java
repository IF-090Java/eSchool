package academy.softserve.eschool.controller;

import academy.softserve.eschool.security.JwtAuthenticationRequest;
import academy.softserve.eschool.security.JwtTokenUtil;
import academy.softserve.eschool.security.JwtUser;
import academy.softserve.eschool.security.exceptions.TokenGlobalTimeExpiredException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AuthenticationController {

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                    @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("signin")
    @ApiOperation("Login to site with username and password. Returns token")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Successfully signed in"),
                    @ApiResponse(code = 400, message = "Bad credentials"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public ResponseEntity<?> createAuthenticationToken
            (@ApiParam(value = "Login and Password", required = true) @RequestBody JwtAuthenticationRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add(tokenHeader, tokenPrefix + token);

        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Refresh token. Requires valid and active token. Returns new token")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Token refreshed"),
                    @ApiResponse(code = 401, message = "Token expired"),
                    @ApiResponse(code = 403, message = "Token cannot be refreshed(Global lifetime expired)"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws TokenGlobalTimeExpiredException {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(tokenHeader, tokenPrefix + jwtTokenUtil.refreshToken(token));
            return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
        } else {
            throw new TokenGlobalTimeExpiredException("Token global lifetime expired");
        }
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
