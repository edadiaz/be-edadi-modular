package com.az.edadi.user.service.impl;

import com.az.edadi.common_service.util.AuthUtils;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.adapter.UserAdapter;
import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.user.model.response.CurrentUserRes;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    public void updateEducationalDegree(UUID userId,UpdateUserEducationInfo request) {
        var user = userRepository.findById(userId).orElseThrow();
        user.setAcademicDegree(request.getDegree());
        user.setSpecialityId(request.getSpecialityId());
        user.setUniversityId(request.getUniversityId());
        userRepository.save(user);

    }
    @Override
    public void updatePersonalInfo(UUID userId, UpdateUserPersonalInfoRequest request) {
        var user = userRepository.findById(userId).orElseThrow();
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthDate());
        userRepository.save(user);
    }

    @Override
    public CurrentUserRes getCurrentUser() {
        User currentUser = userRepository.findById(AuthUtils.getCurrentUserId()).orElseThrow();
        return userAdapter.map(currentUser);
    }
}
