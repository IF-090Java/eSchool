package academy.softserve.eschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@Configuration
@EnableWebMvc
//todo bk don't use deprecated classes. Navigate to WebMvcConfigurerAdapter class and find deprecated section comments for solution
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(
                "/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler(
                "/img/**")
                .addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler(
                "/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ViewResolver viewResolver(){
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(InternalResourceView.class);
        return viewResolver;
    }
}
