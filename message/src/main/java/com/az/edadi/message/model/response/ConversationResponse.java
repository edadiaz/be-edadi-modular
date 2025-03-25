package com.az.edadi.message.model.response;

import com.az.edadi.model.response.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationResponse {
    private String conversationId;
    private List<UserSummaryResponse> userList;
    private String lastMessage;
    private String lastMessageTime;
    private boolean isRead;

}
