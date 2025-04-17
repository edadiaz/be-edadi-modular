package com.az.edadi.user.service;

import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;

public interface UserService {
    void updateEducationalDegree(String userId, UpdateUserEducationInfo request);
    void updatePersonalInfo(String userId, UpdateUserPersonalInfoRequest request);
    CurrentUserResponse getCurrentUser();
}
