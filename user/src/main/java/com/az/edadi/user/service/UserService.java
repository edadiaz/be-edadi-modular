package com.az.edadi.user.service;

import com.az.edadi.model.response.user.UserPageResponse;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserInterestRequest;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.user.model.request.UpdateUserProfileImageRequest;

public interface UserService {
    void updateEducationalDegree(String userId, UpdateUserEducationInfo request);
    void updatePersonalInfo(String userId, UpdateUserPersonalInfoRequest request);
    void updateUserInterests(String userId, UpdateUserInterestRequest request);
    CurrentUserResponse getCurrentUser();
    UserPageResponse findUserById(String id);
    void updateProfileImage(UpdateUserProfileImageRequest newImageRequest);

}
