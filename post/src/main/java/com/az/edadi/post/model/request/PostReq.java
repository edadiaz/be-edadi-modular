package com.az.edadi.post.model.request;

import com.az.edadi.post.model.PostParentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReq {
    private String text;
    private String parentEntityId;
    private PostParentType parentEntityType;
}
