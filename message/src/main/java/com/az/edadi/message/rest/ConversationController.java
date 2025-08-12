package com.az.edadi.message.rest;


import com.az.edadi.message.model.request.CreateConversationRequest;
import com.az.edadi.message.model.response.ConversationResponse;
import com.az.edadi.message.service.ConversationService;
import com.az.edadi.common.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/conversation")
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    ConversationResponse createConversation(@RequestBody CreateConversationRequest request) {
        log.info("User {} is creating conversation with user {}", AuthUtils.getCurrentUserId(), request.getUserId());
        return conversationService.createConversation(request);
    }
    @GetMapping("/me")
    ResponseEntity<List<ConversationResponse>> getConversation(@RequestParam(defaultValue = "0") int page) {
        log.info("User {} is getting his conversation", AuthUtils.getCurrentUserId());
        return ResponseEntity.ok(conversationService.getMyConversation(page));
    }

    @GetMapping("/{id}")
    ResponseEntity<ConversationResponse> getConversation(@PathVariable String id) {
        log.info("User {} try to get conversation {}", AuthUtils.getCurrentUserId(), id);
        return ResponseEntity.ok(conversationService.getConversation(id));
    }

}
