package com.az.edadi.auth.filter;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.TokenBody;
import com.az.edadi.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Timezone, Authorization, X-Requested-With, Accept-Language, remember-me");
        String token = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(token)) {
            token = token.replace("Bearer ", "");
            var tokenBody = jwtService.getTokenBody(TokenType.ACCESS_TOKEN, token);
            setToContext(tokenBody);
        }
        filterChain.doFilter(request, response);

    }

    void setToContext(TokenBody body) {
         List<SimpleGrantedAuthority> authorities = body.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                body.getUserId(),
                "",
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
