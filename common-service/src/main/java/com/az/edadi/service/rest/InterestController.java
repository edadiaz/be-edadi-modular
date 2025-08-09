package com.az.edadi.service.rest;

import com.az.edadi.model.response.interest.InterestResponse;
import com.az.edadi.service.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/common/interest")
public class InterestController {

    private final InterestService interestService;
    @GetMapping
    public ResponseEntity<List<InterestResponse>> getAllInterests() {
        return ResponseEntity.ok(interestService.getAllInterests());
    }
}
