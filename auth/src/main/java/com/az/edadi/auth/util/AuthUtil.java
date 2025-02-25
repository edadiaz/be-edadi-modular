package com.az.edadi.auth.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Optional;

public class AuthUtil {

    public static Optional<String> findCookie(HttpServletRequest request, String cookieName) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> cookieName.equals(cookie.getName()))
                        .map(Cookie::getValue)
                        .findFirst());
    }

}
