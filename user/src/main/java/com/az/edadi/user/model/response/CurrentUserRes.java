package com.az.edadi.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserRes {
    private String id;
    private String username;
    private String name;
    private String email;
    private String universityId;
    private String specialityId;
    private String profileImageUrl;
}
