package com.az.edadi.user.service;

import com.az.edadi.model.request.user.BlockUserRequest;
import com.az.edadi.model.request.user.FollowUserRequest;
import com.az.edadi.model.request.user.ReportUserRequest;
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
    void blockUser(BlockUserRequest request);
    void reportUser(ReportUserRequest request);
    void followUser(FollowUserRequest request);
    void unfollowUser(FollowUserRequest request);

}
