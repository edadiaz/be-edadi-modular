package com.az.edadi.user.adapter;

import com.az.edadi.dal.entity.User;
import com.az.edadi.user.model.response.CurrentUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    public CurrentUserRes map(User user) {
        CurrentUserRes currentUserRes = new CurrentUserRes();
        currentUserRes.setName(user.getName());
        currentUserRes.setEmail(user.getEmail());
        currentUserRes.setId(user.getId());
        currentUserRes.setUsername(user.getUsername());
        currentUserRes.setUniversityId(user.getUniversity_id());
        return currentUserRes;
    }
}
