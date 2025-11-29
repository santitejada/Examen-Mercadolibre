package com.example.mutant.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mutantApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mutant Detector API")
                        .description("API para detección de mutantes basada en secuencias de ADN")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://github.com/santitejada/Examen-Mercadolibre"));
    }
}
