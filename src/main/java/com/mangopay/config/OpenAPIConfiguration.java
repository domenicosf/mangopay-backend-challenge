package com.mangopay.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Maqngopay Api Challenge Test", version = "v1"),
        security = @SecurityRequirement(name = "basicAuth"))
public class OpenAPIConfiguration {
}
