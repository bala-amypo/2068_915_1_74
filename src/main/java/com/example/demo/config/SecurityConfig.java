package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // Authorize requests
            .authorizeHttpRequests(auth -> auth
                // ✅ Allow Swagger UI
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ✅ Allow auth endpoints (optional)
                .requestMatchers("/api/auth/**").permitAll()

                // 🔐 Secure all other endpoints
                .anyRequest().authenticated()
            )

            // Disable default login form
            .formLogin(form -> form.disable())

            // Disable HTTP Basic popup
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
