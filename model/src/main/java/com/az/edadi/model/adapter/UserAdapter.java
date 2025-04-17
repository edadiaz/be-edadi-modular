package com.az.edadi.model.adapter;

import com.az.edadi.dal.entity.user.User;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.model.response.UserSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final String USER_PROFILE_IMAGE = "https://edadi.s3.eu-central-1.amazonaws.com/public/avatar/profile_picture.png";

    public CurrentUserResponse map(User user) {
        CurrentUserResponse currentUserResponse = new CurrentUserResponse();
        currentUserResponse.setName(user.getName());
        currentUserResponse.setEmail(user.getEmail());
        currentUserResponse.setId(user.getId());
        currentUserResponse.setProfileImageUrl(user.getProfilePictureUrl());
        currentUserResponse.setUsername(user.getUsername());
        currentUserResponse.setUniversityId(user.getUniversityId());
        return currentUserResponse;
    }

    public UserSummaryResponse toUserSummaryResponse(User user) {
        UserSummaryResponse userSum = new UserSummaryResponse();
        userSum.setUsername(user.getUsername());
        userSum.setFullName(userSum.getFullName());
        userSum.setId(user.getId());
        userSum.setProfilePictureUrl(user.getProfilePictureUrl());
        return userSum;

    }
}
