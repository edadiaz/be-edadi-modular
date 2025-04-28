package com.az.edadi.auth.model.request;

import com.az.edadi.service.annotation.NotDuplicateEmail;
import com.az.edadi.service.annotation.NotDuplicateUsername;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotDuplicateUsername
    @Length(min = 3,max = 20,message ="valid-username-length")
    private String username;

    @Length(min = 6,max = 20,message ="valid-password-length")
    private String password;

    @NotDuplicateEmail
    @Email(message = "valid-email-format")
    @Length(min = 5,max = 50,message ="valid-email-length")
    private String email;

    @Length(min = 2,max = 50,message ="valid-full-name-length")
    private String fullName;

    private String fingerPrint;

}
