package com.az.edadi.message.adapter;

import com.az.edadi.dal.entity.user.User;
import com.az.edadi.model.response.UserSummaryResponse;
import org.springframework.stereotype.Component;

@Component
public class ConversationAdapter {
    public UserSummaryResponse convertToUserSummaryResponse(User userEntity) {
       var res = new UserSummaryResponse();
       res.setId(userEntity.getId());
       res.setFullName(userEntity.getName());
       res.setUsername(userEntity.getUsername());
       res.setProfilePictureUrl("");
       return res;
    }
}
