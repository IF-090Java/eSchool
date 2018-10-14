package academy.softserve.eschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class Swagger {

    @Bean
    public Docket productAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("academy.softserve.eschool"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Swagger documentation",
                "Swagger documentation for creating schedules and conecting teachers to journals",
                "1.0",
                "Terms of Service",
                new Contact("IF-090.Java", "https://",
                        "email@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
    }

}