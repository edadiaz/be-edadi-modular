package com.az.edadi.user.controller;

import com.az.edadi.model.response.user.UserPageResponse;
import com.az.edadi.service.util.AuthUtils;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserInterestRequest;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import com.az.edadi.model.response.CurrentUserResponse;
import com.az.edadi.user.model.request.UpdateUserProfileImageRequest;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    ResponseEntity<UserPageResponse> findUserById(@PathVariable String userId){
        log.info("User {} fetch own data", AuthUtils.getCurrentUserId());
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PatchMapping("{userId}/profile-picture")
    ResponseEntity<HttpStatus> updateProfileImage(@RequestBody UpdateUserProfileImageRequest newUrl){
        log.info("User {} update profile image", AuthUtils.getCurrentUserId());
        userService.updateProfileImage(newUrl);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PutMapping("{userId}/academic-degree")
    ResponseEntity<HttpStatus> updateUserEducationInfo(@PathVariable String userId, @RequestBody @Validated UpdateUserEducationInfo request) {
        log.info("Updating user education info with userId: {}", "id");
        userService.updateEducationalDegree(userId,request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("{userId}/personal-info")
    ResponseEntity<HttpStatus> updatePersonalInfo(@PathVariable String userId, @RequestBody @Validated UpdateUserPersonalInfoRequest request) {
     log.info("Updating user personal info with userId: {}", "id");
        userService.updatePersonalInfo(userId,request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("{userId}/interest")
    ResponseEntity<HttpStatus> updatePersonalInfo(@PathVariable String userId, @RequestBody @Validated UpdateUserInterestRequest request) {
        log.info("Updating user personal info with userId: {}", "id");
        userService.updateUserInterests(userId,request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @GetMapping("me")
    ResponseEntity<CurrentUserResponse> getMe(){
        log.info("User {} fetch own data", AuthUtils.getCurrentUserId());
        return ResponseEntity.ok(userService.getCurrentUser());
    }


}
