package com.az.edadi.message.service.impl;

import com.az.edadi.dal.entity.message.Conversation;
import com.az.edadi.dal.entity.message.ConversationUser;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.dal.repository.message.ConversationRepository;
import com.az.edadi.dal.repository.message.ConversationUserRepository;
import com.az.edadi.message.adapter.ConversationAdapter;
import com.az.edadi.message.model.request.CreateConversationRequest;
import com.az.edadi.message.model.response.ConversationResponse;
import com.az.edadi.message.service.ConversationService;
import com.az.edadi.model.adapter.UserAdapter;
import com.az.edadi.service.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationUserRepository conversationUserRepository;
    private final UserRepository userRepository;
    private final ConversationAdapter conversationAdapter;
    private final UserAdapter userAdapter;

    @Override
    public ConversationResponse createConversation(CreateConversationRequest request) {

        long a = System.currentTimeMillis();
        var conversationUsers = conversationUserRepository.findConversationIdForUsers(AuthUtils.getCurrentUserId(), request.getUserId());
        if (conversationUsers.size() == 2)
            return getConversation(conversationUsers.get(0).getConversationId());
        String conversationId = createConversation();
        createConversationUser(conversationId, AuthUtils.getCurrentUserId());
        createConversationUser(conversationId, request.getUserId());
        return getConversation(conversationId);
    }


    @Override
    public ConversationResponse getConversation(String conversationId) {
        var conversationUsers = conversationUserRepository.findByConversationId(conversationId);
        if (conversationUsers.size() != 2)
            throw new RuntimeException("Invalid conversation");
        var userList = userRepository.findByIdIn(conversationUsers.stream().map(ConversationUser::getUserId).toList())
                .stream()
                .map(userAdapter::toUserSummaryResponse).toList();
        var conversationResponse = new ConversationResponse();
        conversationResponse.setConversationId(conversationId);
        conversationResponse.setUserList(userList);
        return conversationResponse;
    }

    @Override
    public List<ConversationResponse> getMyConversation(Integer page) {
        var userId = AuthUtils.getCurrentUserId();
        var conversationUsers = conversationUserRepository.findRecentConversations(userId, page * 10, 10);
        return null;
    }

    String createConversation() {
        Conversation conversation = new Conversation();
        conversation.setCreatedDate(LocalDateTime.now());
        return conversationRepository.save(conversation).getId();
    }

    void createConversationUser(String conversationId, String userId) {
        ConversationUser conversationUser = new ConversationUser();
        conversationUser.setConversationId(conversationId);
        conversationUser.setUserId(userId);
        conversationUserRepository.save(conversationUser);
    }


}
