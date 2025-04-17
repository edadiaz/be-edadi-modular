package com.az.edadi.message.service;

import com.az.edadi.message.model.request.UserChatMessage;

import java.security.Principal;

public interface MessageService {
    void sendMessage(Principal userId, UserChatMessage message);
}
