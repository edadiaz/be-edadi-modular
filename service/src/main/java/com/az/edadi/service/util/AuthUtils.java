package com.az.edadi.service.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@UtilityClass
public class AuthUtils {
    public List<String> getPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).map(Authentication::getPrincipal).orElseThrow().toString();
    }

    public static void isCurrentUser(String id) {
        String currentUserId = getCurrentUserId();
        if (!currentUserId.equals(id)) {
            throw new IllegalArgumentException("You are not authorized to perform this action");
        }

    }

}
