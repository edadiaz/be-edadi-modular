package com.az.edadi.post.rest;

import com.az.edadi.model.request.post.ReactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reaction")
public class ReactionController {

    @PostMapping
    ResponseEntity<HttpStatus> sendReaction(@RequestBody ReactionRequest postRequest) {
         return ResponseEntity.ok(HttpStatus.CREATED);
    }


}
