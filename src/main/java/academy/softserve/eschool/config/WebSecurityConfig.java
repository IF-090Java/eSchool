package academy.softserve.eschool.config;

import academy.softserve.eschool.security.CustomPasswordEncoder;
import academy.softserve.eschool.security.exceptions.ExceptionHandlerFilter;
import academy.softserve.eschool.security.JwtAuthenticationEntryPoint;
import academy.softserve.eschool.security.JwtAuthorizationTokenFilter;
import academy.softserve.eschool.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${security.password.encodingKey}")
    private String key;

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Autowired
    ExceptionHandlerFilter exceptionHandlerFilter;

    private static String authenticationPath = "/signin";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public CustomPasswordEncoder passwordEncoderBean() {
        return new CustomPasswordEncoder(key);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers( "/favicon.ico").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/refresh").permitAll()
                .antMatchers("/requestPasswordReset").permitAll()
                .antMatchers("/resetPassword").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/ui/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(exceptionHandlerFilter , CorsFilter.class)
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                        HttpMethod.POST,
                        authenticationPath
                )
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.png",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )
                .antMatchers("/h2/**")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
