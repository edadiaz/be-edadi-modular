package com.az.edadi.user.service.impl;

import com.az.edadi.model.request.user.BlockUserRequest;
import com.az.edadi.model.request.user.FollowUserRequest;
import com.az.edadi.model.request.user.ReportUserRequest;
import com.az.edadi.service.adapter.user.UserAdapter;
import com.az.edadi.dal.entity.user.UserInterest;
import com.az.edadi.dal.repository.user.UserInterestRepository;
import com.az.edadi.model.response.user.UserPageResponse;
import com.az.edadi.service.util.AuthUtils;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.user.UserRepository;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserInterestRequest;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.user.model.request.UpdateUserProfileImageRequest;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInterestRepository userInterestRepository;
    private final UserAdapter userAdapter;

    @Override
    public void updateEducationalDegree(String userId, UpdateUserEducationInfo request) {
        AuthUtils.isCurrentUser(userId);
        var user = userRepository.findById(userId).orElseThrow();
        user.setAcademicDegree(request.getDegree());
        user.setSpecialityId(request.getSpecialityId());
        user.setUniversityId(request.getUniversityId());
        user.setGroup(request.getGroup());
        userRepository.save(user);

    }

    @Override
    public void updatePersonalInfo(String userId, UpdateUserPersonalInfoRequest request) {
        AuthUtils.isCurrentUser(userId);
        var user = userRepository.findById(userId).orElseThrow();
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthDate());
        userRepository.save(user);
    }

    @Override
    public void updateUserInterests(String userId, UpdateUserInterestRequest request) {
        AuthUtils.isCurrentUser(userId);
        var user = userRepository.findById(userId).orElseThrow();
        var newUserInterests = userInterestRepository.findAllById(request.stringList()).stream().map(
                i -> {
                    UserInterest ui = new UserInterest();
                    ui.setUserId(userId);
                    ui.setInterestId(i.getInterestId());
                    return ui;
                }
        ).collect(Collectors.toSet());
        userInterestRepository.saveAll(newUserInterests);
    }

    @Override
    public CurrentUserResponse getCurrentUser() {
        User currentUser = userRepository.findById(AuthUtils.getCurrentUserId()).orElseThrow();
        return userAdapter.mapToCurrentUserResponse(currentUser);
    }

    @Override
    public UserPageResponse findUserById(String userId) {
        var user = userRepository.findById(userId).orElseThrow();
        return userAdapter.mapToUserPageResponse(user);
    }

    @Override
    public void updateProfileImage(UpdateUserProfileImageRequest newImageRequest) {
        var user  = userRepository.findById(AuthUtils.getCurrentUserId()).orElseThrow();
        user.setProfilePictureUrl(newImageRequest.getNewUrl());
        userRepository.save(user);

    }

    @Override
    public void blockUser(BlockUserRequest request) {

    }

    @Override
    public void reportUser(ReportUserRequest request) {

    }

    @Override
    public void followUser(FollowUserRequest request) {

    }

    @Override
    public void unfollowUser(FollowUserRequest request) {

    }


}
