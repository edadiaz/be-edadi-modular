package com.az.edadi.user.service.impl;

import com.az.edadi.dal.entity.user.UserInterest;
import com.az.edadi.dal.repository.user.InterestRepository;
import com.az.edadi.dal.repository.user.UserInterestRepository;
import com.az.edadi.service.util.AuthUtils;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.user.UserRepository;
import com.az.edadi.model.adapter.UserAdapter;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserInterestRequest;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;
    private final UserInterestRepository userInterestRepository;
    private final InterestRepository interestRepository;

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
        User currentUser = userRepository.findById(AuthUtils.getCurrentUserId().toString()).orElseThrow();
        return userAdapter.map(currentUser);
    }


}
