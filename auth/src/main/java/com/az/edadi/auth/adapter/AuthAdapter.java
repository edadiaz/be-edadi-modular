package com.az.edadi.auth.adapter;

import com.az.edadi.auth.model.response.OAuth2CustomUser;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.types.Provider;
import com.az.edadi.common.constant.EdadiImageLinks;
import com.az.edadi.auth.model.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        user.setProvider(Provider.NATIVE);
    }

    public void map(User user, OAuth2CustomUser request, Provider provider) {
        user.setName(request.getName());
        user.setEmail(request.getEmail().toLowerCase());
        user.setProfilePictureUrl(request.getPicture());
        user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString().substring(0, 10)));
        var emailString = request.getEmail()
                .split("@")[0]
                .replaceAll("[^a-zA-Z0-9]", "");
        var randomUsername = String.join(emailString, UUID.randomUUID().toString().substring(0, 4));
        user.setUsername(randomUsername);
        user.setProvider(provider);
    }

}
