package com.az.edadi.model.response.post;

import com.az.edadi.common.constant.post.PostParentType;
import com.az.edadi.model.response.user.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String content;
    private String parentEntityId;
    private PostParentType parentEntityType;
    private UserSummaryResponse user;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDate createdDate;
}
