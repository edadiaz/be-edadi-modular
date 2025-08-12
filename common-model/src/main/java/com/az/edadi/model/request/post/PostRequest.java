package com.az.edadi.model.request.post;

import com.az.edadi.common.constant.post.PostParentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @NotBlank
    private String content;
    @NotBlank
    private String parentId;
    @NotNull
    private PostParentType parentEntityType;
    private List<String> attachmentId;
}
