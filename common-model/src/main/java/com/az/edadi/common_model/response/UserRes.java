package com.az.edadi.common_model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    private UUID id;
    private String username;
    private String fullName;
    private String profilePicUrl;

}
