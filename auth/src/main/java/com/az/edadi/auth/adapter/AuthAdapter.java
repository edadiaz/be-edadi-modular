package com.az.edadi.auth.adapter;

import com.az.edadi.common_model.response.UserRes;
import com.az.edadi.dal.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthAdapter {
    public UserRes map(User user) {
        UserRes userRes = new UserRes();
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setFullName(user.getName());
        userRes.setProfilePicUrl("");
        return userRes;
    }
}
