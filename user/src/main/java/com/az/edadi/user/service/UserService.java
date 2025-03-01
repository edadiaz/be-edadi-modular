package com.az.edadi.user.service;

import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.user.model.response.CurrentUserRes;

public interface UserService {
    void updateEducationalDegree(UpdateUserEducationInfo request);
    void updatePersonalInfo(UpdateUserPersonalInfoRequest request);
    CurrentUserRes getCurrentUser();
}
