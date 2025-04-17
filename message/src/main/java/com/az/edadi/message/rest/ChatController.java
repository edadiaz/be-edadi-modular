package com.az.edadi.message.rest;

import com.az.edadi.message.model.request.UserChatMessage;
import com.az.edadi.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessageService messageService;
    @MessageMapping("/chat")
    public void sendMessage(UserChatMessage message, Principal principal) {
        messageService.sendMessage(principal, message);
    }

}
