package com.az.edadi.user.adapter;

import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.types.AcademicDegree;
import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.response.CurrentUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final PasswordEncoder passwordEncoder;

    public void map(User user, RegisterUserRequest request) {
        user.setName(request.getFullName().toLowerCase());
        user.setEmail(request.getEmail().toLowerCase());
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAcademicDegree(AcademicDegree.NONE.toString());
    }

    public CurrentUserRes map(User user) {
        CurrentUserRes currentUserRes = new CurrentUserRes();
        currentUserRes.setName(user.getName());
        currentUserRes.setEmail(user.getEmail());
        currentUserRes.setId(user.getId());
        currentUserRes.setUsername(user.getUsername());
        currentUserRes.setUniversityId(user.getUniversity_id());
        return currentUserRes;
    }
}
