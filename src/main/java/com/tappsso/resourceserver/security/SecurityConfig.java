package com.tappsso.resourceserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        return http
                .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/api/public").permitAll()
                    .requestMatchers("/api/private").authenticated()
                    .requestMatchers("/api/private-read").hasAuthority("SCOPE_developer.read")
                    .requestMatchers("/api/private-write").hasAuthority("SCOPE_developer.write")
                    .requestMatchers("/api/private-admin").hasAuthority("SCOPE_developer.admin")
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(withDefaults())
                )
                .build();
    }
}
