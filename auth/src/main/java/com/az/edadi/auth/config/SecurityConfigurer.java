package com.az.edadi.auth.config;

import com.az.edadi.auth.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer {

    private final JwtAuthenticationFilter jwtVerifierFilter;

    private final String[] POST_WHITE_LIST = {
            "/api/v1/user/sign-up",
            "/api/v1/auth/login-with-password",
            "/api/v1/file-storage/**",
            "api/v1/university/**"
    };
    private final String[] GET_WHITE_LIST = {
            "/api/v1/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"

    };

    private final String[] ALL_ALLOWED = {
            "/h2-console/**"


    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, GET_WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, POST_WHITE_LIST).permitAll()
                        .requestMatchers(ALL_ALLOWED).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}