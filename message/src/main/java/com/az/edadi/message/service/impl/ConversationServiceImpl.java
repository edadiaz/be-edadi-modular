package com.az.edadi.message.service.impl;

import com.az.edadi.dal.entity.message.Conversation;
import com.az.edadi.dal.entity.message.ConversationUser;
import com.az.edadi.dal.repository.message.ConversationRepository;
import com.az.edadi.dal.repository.message.ConversationUserRepository;
import com.az.edadi.message.model.request.CreateConversationRequest;
import com.az.edadi.message.model.response.ConversationResponse;
import com.az.edadi.message.service.ConversationService;
import com.az.edadi.service.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationUserRepository conversationUserRepository;

    @Override
    public ConversationResponse createConversation(CreateConversationRequest request) {
        Conversation conversation = new Conversation();
        conversation.setCreatedDate(LocalDateTime.now());
        conversation = conversationRepository.save(conversation);
        ConversationUser conversationUser1 = new ConversationUser();
        conversationUser1.setUserId(AuthUtils.getCurrentUserId());
        conversationUser1.setConversationId(conversation.getId());
        conversationUserRepository.save(conversationUser1);
        ConversationUser conversationUser2 = new ConversationUser();
        conversationUser2.setUserId(request.getUserId());
        conversationUser2.setConversationId(conversation.getId());
        conversationUserRepository.save(conversationUser1);
        return null;
    }

    @Override
    public ConversationResponse getConversation(String conversationId) {
        return null;
    }
}
