package com.project.fitnesstracker.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Fitness Tracking API's")
                        .description("Fitness Tracking API's")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ruthvik Wadkar")
                                .email("ruthvikwadkar@gmail.com")
                             
                        )
                );
    }
}

