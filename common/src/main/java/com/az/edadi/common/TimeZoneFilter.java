package com.az.edadi.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class TimeZoneFilter extends OncePerRequestFilter {

    public static final ThreadLocal<ZoneId> USER_ZONE = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var zoneId = request.getHeader("X-Timezone");
        var timeZone = Optional.ofNullable(zoneId).filter(StringUtils::hasText).orElse("Asia/Baku");

        try {
            USER_ZONE.set(ZoneId.of(timeZone));
        } catch (DateTimeException e) {
            USER_ZONE.set(ZoneId.of("Asia/Baku"));
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            USER_ZONE.remove();
        }
    }
}


