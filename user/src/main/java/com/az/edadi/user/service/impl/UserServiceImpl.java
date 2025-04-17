package com.az.edadi.user.service.impl;

import com.az.edadi.service.util.AuthUtils;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.model.adapter.UserAdapter;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    public void updateEducationalDegree(String userId,UpdateUserEducationInfo request) {
        var user = userRepository.findById(userId.toString()).orElseThrow();
        user.setAcademicDegree(request.getDegree());
        user.setSpecialityId(request.getSpecialityId());
        user.setUniversityId(request.getUniversityId());
        userRepository.save(user);

    }
    @Override
    public void updatePersonalInfo(String userId, UpdateUserPersonalInfoRequest request) {
        var user = userRepository.findById(userId.toString()).orElseThrow();
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthDate());
        userRepository.save(user);
    }

    @Override
    public CurrentUserResponse getCurrentUser() {
        User currentUser = userRepository.findById(AuthUtils.getCurrentUserId().toString()).orElseThrow();
        return userAdapter.map(currentUser);
    }
}
