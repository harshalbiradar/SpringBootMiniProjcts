package com.harshalit.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2) 
          .groupName("public-api")
          .apiInfo(apiInfo())
          .select()                                
          .apis(RequestHandlerSelectors.basePackage("com.harshalit.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("UserManagement-API")
				.description("Ashok IT API Reference for developers")
				.termsOfServiceUrl("www.ashokit.in")
				.contact("ashokitschool@gmail.com")
				.license("Ashok IT")
				.licenseUrl("ashokitschool@gmail.com")
				.version("1.0")
				.build();
	}
	
	
	
}
