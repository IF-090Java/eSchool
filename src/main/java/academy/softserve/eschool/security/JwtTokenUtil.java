package academy.softserve.eschool.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     *
     */
    @Value("${jwt.globalExpiration}")
    private Long globalExpiration;

    /**
     *  Method that returns username extracted from JwtToken
     *
     * @param token Jwt token
     * @return Name of user, or null if not present
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     *  Method that returns date when specified token was issued
     *
     * @param token Jwt token
     * @return Date when token was created, or null if not present
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     *  Method that returns expiration date of specified token
     *
     * @param token Jwt token
     * @return Date after is no valid anymore, or null if not present
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Returns some claim received from token
     *
     * @param token Jwt token, from which information will be extracted
     * @param claimsResolver Function that will be applied to get token
     * @param <T> Type of the result of function
     * @return Claim extracted from token
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Gets {@link Claims} from specified token.
     * Is used to get certain parameter from token body in {@link this.getClaimFromToken}
     *
     * @param token Jwt token
     * @return Claims (Jwt body) from from specified token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if specified token is expired
     * @param token Jwt token
     * @return True if token is expired, otherwise false
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    /**
     * Creates encoded Jwt token from {@link JwtUser} object.
     * Token is signed with {@code secret} key and hs512 algorithm.
     * Token contains username, id of user, Roles of user, date when it was issued and expiration date.
     * @param userDetails Object of {@link JwtUser}
     * @return Jwt token
     */
    public String generateToken(JwtUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Roles",userDetails.getAuthorities().get(0));
        return doGenerateToken(claims, userDetails.getUsername(), userDetails.getId());
    }

    /**
     * Creates Jwt token based on passed parameters.
     * Token is signed with {@code secret} key and hs512 algorithm.
     * Token contains username, id of user, Roles of user, date when it was issued and expiration date
     * @param claims Map of claims for this token
     * @param subject Username
     * @param id Id of user
     * @return Jwt token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject, Long id) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(String.valueOf(id))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Checks if token can be refreshed.
     * @param token Jwt token
     * @return True if token can be refreshed, otherwise false
     */
    public Boolean canTokenBeRefreshed(String token) {
        final Date created = getIssuedAtDateFromToken(token);
        final Date curDate = clock.now();

        return ((curDate.getTime() - created.getTime())<globalExpiration * 1000)
                && !isTokenExpired(token);
    }

    /**
     * Method refreshes token
     * Returns token with the same claims that was passed but with expiration date increased of {@code expiration}
     *
     * @param token Token to be refreshed
     * @return Refreshed token
     */
    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(getIssuedAtDateFromToken(token));
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Method check if token is valid.
     * If subject of token is not equal to username of passed UserDeatils returns false.
     * If token is expired returns false
     *
     * @param token Jwt token that should be validated
     * @param userDetails
     * @return True if token is valid, otherwise false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && user.isEnabled()
        );
    }


    /**
     * Returns expiration date, calculated based on date of creation and {@code expiration} value
     * @param createdDate Date of creation
     * @return Date of expiration
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

}
