package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.AlgorithmParameterGenerator;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class SecurityConfig
{
    String[]                                  URL =
    { "/**" };
    private final JwtAuthFilter               jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http )
        throws Exception
    {
        return http.csrf( csrf -> csrf.disable() ).cors( cors -> cors.configurationSource( corsConfigurationSource() ) )
                .authorizeHttpRequests( auth -> auth.requestMatchers( URL ).permitAll().anyRequest().authenticated() )
                .exceptionHandling( e -> e.authenticationEntryPoint( jwtAuthenticationEntryPoint ) )
                .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class ).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( List.of( "http://localhost:4200" ) );
        configuration.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE", "OPTIONS" ) );
        configuration.setAllowedHeaders( List.of( "Authorization", "Content-Type", "token" ) );
        configuration.setExposedHeaders( List.of( "token" ) );
        configuration.setAllowCredentials( true );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", configuration );
        return source;
    }

    @Bean
    public AuthenticationManager authManager( AuthenticationConfiguration authenticationConfiguration )
        throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
