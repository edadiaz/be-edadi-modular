package com.az.edadi.user.model;

import com.az.edadi.user.validation.NotDuplicateEmail;
import com.az.edadi.user.validation.NotDuplicateUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotDuplicateUsername
    private String username;

    private String password;

    @NotDuplicateEmail
    private String email;

    private String fullName;

}
