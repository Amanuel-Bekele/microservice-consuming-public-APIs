package com.altimetrik.music.documentation;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.altimetrik.music.controller"))
				.paths(regex("/altimetrik/music.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Altimetrik Music API")
				.description("\"Search for Artist, top tracks and albums\"").version("1.0.0").license("None")
				.licenseUrl("").contact(new Contact("Amanuel Bekele", "","amanuelbekele32@gmail.com")).build();
	}

}
