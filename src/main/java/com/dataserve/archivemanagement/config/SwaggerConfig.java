//package com.dataserve.archivemanagement.config;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dataserve.archivemanagement")).build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Archive Management App REST API",
//                "This is the back end Archive Management APIs provied by spring boot ",
//                "API TOS",
//                "Terms of service",
//                new Contact("", "", ""),
//                "License of API", "API license URL", Collections.emptyList());
//    }
//}
