package com.example.ams.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ams.controller.restapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    @SuppressWarnings("deprecation")
    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfo("AMS API Documentation",
                "API for Article Management",
                "1.0",
                "http://www.example.com",
                "XYZ",
                "http://www.example.com",
                "http://www.example.com");
    }

    @Configuration
    public static class SwaggerCofig implements WebMvcConfigurer{
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/api-docs").setViewName("swagger/index");
        }
    }
}
