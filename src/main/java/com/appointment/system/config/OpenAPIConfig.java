package com.appointment.system.config;


import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Doctor Appointment System API")
                        .version("1.0")
                        .description("REST API documentation for Doctor, Patient, and Appointment modules."));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("doctor-api")
                .pathsToMatch("/**")
                .build();
    }
}
