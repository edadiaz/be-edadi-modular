package com.az.edadi.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenBody {
    private String tokenId;
    private String userId;
    private List<String> permissions;
}
