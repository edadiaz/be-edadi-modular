package com.az.edadi.user.service.impl;

import com.az.edadi.common_service.util.AuthUtils;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.no_sql.repository.UserRepository;
import com.az.edadi.user.adapter.UserAdapter;
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
    public CurrentUserRes getCurrentUser() {
        User currentUser = userRepository.findById(AuthUtils.getCurrentUserId().toString()).orElseThrow();
        return userAdapter.map(currentUser);
    }
}
