package com.az.edadi.auth.security;

import com.az.edadi.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String origin = request.getHeader("Origin");
        if (origin != null)
            response.setHeader("Access-Control-Allow-Origin", origin);

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Accept-Language", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Accept-Language, remember-me");
        String token = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(token)) {
            token = token.replace("Bearer ", "");
            jwtService.verifyAccessToken(token);
        }
        filterChain.doFilter(request, response);

    }
}
