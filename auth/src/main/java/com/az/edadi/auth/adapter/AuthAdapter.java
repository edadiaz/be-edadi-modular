package com.az.edadi.auth.adapter;

import com.az.edadi.dal.entity.user.User;
import com.az.edadi.model.constant.EdadiImageLinks;
import com.az.edadi.auth.model.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapter {
    private final PasswordEncoder passwordEncoder;

    public void map(User user, RegisterUserRequest request) {
        user.setName(request.getFullName().toLowerCase());
        user.setEmail(request.getEmail().toLowerCase());
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProfilePictureUrl(EdadiImageLinks.USER_DEFAULT_PROFILE_PICTURE);
    }

}
