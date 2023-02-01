package com.interview.spring.codelitt.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.interview.spring.codelitt.entrypoint";

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage(BASE_PACKAGE))
                .paths(any())
                .build()
                //.globalResponseMessage(RequestMethod.GET, listResponseMessage())
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("Codelitt API")
                .description("API Documentation")
                .version("1.0.0")
                .license("Apache Licence Version 2.0")
                .build();
    }


}
