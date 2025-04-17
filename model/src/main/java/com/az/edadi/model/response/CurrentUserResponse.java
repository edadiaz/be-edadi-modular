package com.az.edadi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserResponse {
    private String id;
    private String username;
    private String name;
    private String email;
    private String universityId;
    private String specialityId;
    private String profileImageUrl;
}
