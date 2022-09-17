package br.edu.infnet.moviesbattle.api.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
    private static final String SCHEME_NAME = "basicAuth";
    private static final String SCHEME = "basic";
 
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                		.addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
                	.addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .info(new Info()
                		.title("Movies Battle API")
                		.description("Rest API for movies battle game.")
                		.version("v1.0.0"));
    }
    
    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }
}
