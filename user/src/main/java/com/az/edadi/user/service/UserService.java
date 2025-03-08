package com.az.edadi.user.service;

import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.user.model.response.CurrentUserRes;

import java.util.UUID;

public interface UserService {
    void updateEducationalDegree(String userId, UpdateUserEducationInfo request);
    void updatePersonalInfo(String userId, UpdateUserPersonalInfoRequest request);
    CurrentUserRes getCurrentUser();
}
