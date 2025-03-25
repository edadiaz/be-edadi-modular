package com.az.edadi.message.service;

import com.az.edadi.message.model.request.CreateConversationRequest;
import com.az.edadi.message.model.response.ConversationResponse;

import java.util.List;

public interface ConversationService {
    ConversationResponse createConversation(CreateConversationRequest request);
    ConversationResponse getConversation(String conversationId);
    List<ConversationResponse> getMyConversation(Integer page);

}
