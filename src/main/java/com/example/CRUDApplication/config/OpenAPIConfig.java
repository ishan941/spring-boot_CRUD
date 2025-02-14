package com.example.CRUDApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1", description = "This is my API documentation"), security = @SecurityRequirement(name = "bearerAuth"))
public class OpenAPIConfig {
}
