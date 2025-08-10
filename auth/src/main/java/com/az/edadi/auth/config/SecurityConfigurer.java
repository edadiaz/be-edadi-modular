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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer {

    private final JwtAuthenticationFilter jwtVerifierFilter;

    private final String[] POST_WHITE_LIST = {
            "/api/v1/auth/sign-up",
            "/api/v1/auth/login-with-password",
            "/api/v1/auth/login-with-google",
            "/api/v1/auth/forgot-password",
            "/api/v1/auth/refresh-token",
            "/api/v1/auth/reset-password-with-token",
            "/api/v1/university/**"
    };
    private final String[] GET_WHITE_LIST = {
            "/api/v1/common/**",
            "api/v1/docs",
            "/api/v1/notification/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui/index.html"

    };

    private final String[] ALL_ALLOWED = {
            "/h2-console/**",
            "/api/v1/docs",
            "/ws/**",
            "/topic/**",
            "/app/**"

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, GET_WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, POST_WHITE_LIST).permitAll()
                        .requestMatchers(ALL_ALLOWED).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }


}