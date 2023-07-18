package com.gradleJSP.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                // swagger API 문서 설명
                .apiInfo(new ApiInfoBuilder()
                        .title("타이틀")
                        .description("설명")
                        .contact(new Contact("[My Repository]", "https://github.com/Aeius/StudyJava","psb6604@gmail.com"))
                        .license("Copyrights by me!")
                        .termsOfServiceUrl("http://localhost:8080/")
                        .version("0.0.0")
                        .build())
                .select()
                // swagger API 문서로 만들기 원하는 BasePackage 경로(필수)
                .apis(RequestHandlerSelectors.any())
                // URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서화(필수
                .paths(PathSelectors.any())
                .build();
    }
}
