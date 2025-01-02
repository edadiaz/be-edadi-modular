package com.az.edadi.auth.rest;

import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @PostMapping("login/with-password")
    ResponseEntity<Void> loginWithPassword(@RequestBody LoginWithPasswordRequest request){
        
        return null;
    }
}
