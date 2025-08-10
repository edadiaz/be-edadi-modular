package com.az.edadi.service.adapter.user;

import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.institution.InstitutionRepository;
import com.az.edadi.model.constant.PageTab;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.model.response.user.UserPageResponse;
import com.az.edadi.model.response.user.UserSummaryResponse;
import com.az.edadi.service.adapter.speciality.SpecialityAdapter;
import com.az.edadi.service.adapter.university.UniversityAdapter;
import com.az.edadi.service.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter {

    private final SpecialityAdapter specialityAdapter;
    private final UniversityAdapter universityAdapter;
    private final InstitutionRepository universityRepository;


    private final String USER_PROFILE_IMAGE = "https://edadi.s3.eu-central-1.amazonaws.com/public/avatar/profile_picture.png";


    public UserPageResponse mapToUserPageResponse(User user) {
        var userPageResponse = UserPageResponse.builder();
        var institutionSummary  = Optional.ofNullable(user.getUniversityId()).map(id -> universityRepository.findById(id)
                .orElse(null));
        institutionSummary.ifPresent(institution -> userPageResponse.institution(universityAdapter.mapToSummary(institution)));
        return userPageResponse
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getName())
                .profileImageUrl(user.getProfilePictureUrl())
                .specialityId(user.getSpecialityId())
                .isCurrentUser(AuthUtils.isCurrentUser(user.getId()))
                .pageTabs(getUserTabs())
                .build();

    }


    List<PageTab> getUserTabs() {
        return List.of(
                PageTab.POSTS
        );
    }

    public CurrentUserResponse mapToCurrentUserResponse(User user) {
        CurrentUserResponse currentUserResponse = new CurrentUserResponse();
        currentUserResponse.setName(user.getName());
        currentUserResponse.setEmail(user.getEmail());
        currentUserResponse.setId(user.getId());
        currentUserResponse.setProfileImageUrl(user.getProfilePictureUrl());
        currentUserResponse.setUsername(user.getUsername());
        currentUserResponse.setUniversityId(user.getUniversityId());
        return currentUserResponse;
    }

    public UserSummaryResponse mapToUserSummary(User user) {
        UserSummaryResponse userSum = new UserSummaryResponse();
        userSum.setUsername(user.getUsername());
        userSum.setFullName(userSum.getFullName());
        userSum.setId(user.getId());
        userSum.setProfilePictureUrl(user.getProfilePictureUrl());
        return userSum;
    }

}
