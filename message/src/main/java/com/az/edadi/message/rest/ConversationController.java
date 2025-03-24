package com.az.edadi.message.rest;

import com.az.edadi.message.model.request.CreateConversationRequest;
import com.az.edadi.message.model.response.ConversationResponse;
import com.az.edadi.message.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/conversation")
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    ConversationResponse createConversation(@RequestBody CreateConversationRequest request) {
      return conversationService.createConversation(request);
    }

    @GetMapping("/{conversationId}")
    ConversationResponse getConversation(@PathVariable String conversationId) {
      return conversationService.getConversation(conversationId);
    }
}
