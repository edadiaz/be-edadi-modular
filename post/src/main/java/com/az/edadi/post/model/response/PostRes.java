package com.az.edadi.post.model.response;

import com.az.edadi.model.response.UserSummaryResponse;
import com.az.edadi.post.model.PostParentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRes {
    private UUID id;
    private String text;
    private String parentEntityId;
    private PostParentType parentEntityType;
    private UserSummaryResponse user;
}
