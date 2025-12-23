package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http )
        throws Exception
    {
        http.csrf( csrf -> csrf.disable() ).cors( cors -> cors.configurationSource( request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins( List.of( "http://localhost:4200" ) );
            config.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE", "OPTIONS" ) );
            config.setAllowedHeaders( List.of( "*" ) );
            config.setAllowCredentials( true );
            return config;
        } ) ).authorizeHttpRequests( auth -> auth.requestMatchers( "/auth/**", "/users/**", "/ws/**" ).permitAll()
                .anyRequest().authenticated() ).httpBasic( Customizer.withDefaults() );
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
