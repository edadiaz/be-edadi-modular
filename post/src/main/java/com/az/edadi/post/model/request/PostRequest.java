package com.az.edadi.post.model.request;

import com.az.edadi.post.model.PostParentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String text;
    private String parentEntityId;
    private PostParentType parentEntityType;
    private List<String> attachmentId;
}
