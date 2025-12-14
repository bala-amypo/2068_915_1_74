
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAI api(){
        return new OpenAPI().components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().type()))
    }
}
