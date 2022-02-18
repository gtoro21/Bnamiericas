package com.bnamericas;


import java.io.Serializable;

//import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigAdapter extends WebMvcConfigurationSupport implements Serializable {

	private static final long serialVersionUID = 9220562847665492110L;		
// configuraciones para poder utilizar una interfaz grafica de test. 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor())
				.excludePathPatterns(new String[] { "/v2/**", "/swagger-ui.html", "/v2/api-docs?group=public-api",
						"/swagger-resources", "/webjars/**", "/configuration/**", "/pruebas*" });
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
				.allowedOrigins("*").allowedHeaders("*").exposedHeaders("Authorization").allowCredentials(false);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error.*"))).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Leantech Employee").description("Leantech Employee")
				.license("JavaInUse License").version("1.0").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}