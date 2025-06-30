package com.az.edadi.message.service.impl;

import com.az.edadi.dal.entity.message.ConversationUser;
import com.az.edadi.dal.repository.user.UserRepository;
import com.az.edadi.dal.repository.message.ConversationUserRepository;
import com.az.edadi.dal.repository.message.MessageRepository;
import com.az.edadi.message.adapter.MessageAdapter;
import com.az.edadi.message.model.request.UserChatMessage;
import com.az.edadi.message.model.response.MessageResponse;
import com.az.edadi.message.service.MessageService;
import com.az.edadi.model.adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final ConversationUserRepository conversationUserRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageAdapter messageAdapter;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    public void sendMessage(Principal user, UserChatMessage message) {
        var userConversations = conversationUserRepository.findByConversationId(message.getConversationId());
        userConversations.stream().filter(userConversation -> userConversation.getUserId().equals(user.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found in conversation"));
        var messageEnt = messageAdapter.toMessage(message, user.getName());
        messageEnt= messageRepository.save(messageEnt);
        var userEnt = userRepository.findById(user.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        var messageRes = messageAdapter.toMessageResponse(messageEnt,
                userAdapter.toUserSummaryResponse(userEnt));
        notifyUsers(userConversations, messageRes);
    }

    void notifyUsers(List<ConversationUser> users, MessageResponse message) {
        users.forEach(user -> {
            messagingTemplate.convertAndSendToUser(user.getUserId(), "/topic/messages", message);
        });
    }


}
