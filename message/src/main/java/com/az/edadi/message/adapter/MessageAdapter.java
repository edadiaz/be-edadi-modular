package com.az.edadi.message.adapter;

import com.az.edadi.dal.entity.message.Message;
import com.az.edadi.message.model.request.UserChatMessage;
import com.az.edadi.message.model.response.MessageResponse;
import com.az.edadi.model.response.UserSummaryResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageAdapter {
    public MessageResponse toMessageResponse(Message message, UserSummaryResponse userSum) {
        var res = new MessageResponse();
        res.setId(message.getId());
        res.setMessageTime(message.getCreateTs());
        res.setConversationId(message.getConversationId());
        res.setMessage(message.getText());
        res.setSender(userSum);
        return res;
    }

    public Message toMessage(UserChatMessage message, String userId) {
        var res = new Message();
        res.setConversationId(message.getConversationId());
        res.setText(message.getMessage());
        res.setCreateTs(LocalDateTime.now());
        res.setUserId(userId);
        return res;
    }
}
