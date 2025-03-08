package com.az.edadi.user.controller;

import com.az.edadi.common_service.util.AuthUtils;
import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.user.model.response.CurrentUserRes;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PutMapping("{userId}/academic-degree")
    ResponseEntity<HttpStatus> updateUserEducationInfo(@PathVariable String userId, @RequestBody @Validated UpdateUserEducationInfo request) {
        log.info("Updating user education info with userId: {}", "id");
        userService.updateEducationalDegree(userId,request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("{userId}/personal-info")
    ResponseEntity<HttpStatus> updatePersonalInfo(@PathVariable String userId, @RequestBody @Validated UpdateUserPersonalInfoRequest request) {
     log.info("Updating user personal info with userId: {}", "id");
        userService.updatePersonalInfo(userId,request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping("me")
    ResponseEntity<CurrentUserRes> getMe(){
        log.info("User {} fetch own data", AuthUtils.getCurrentUserId());
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
