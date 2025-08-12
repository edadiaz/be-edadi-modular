package com.az.edadi.dal.entity.post;

import com.az.edadi.common.constant.post.PostParentType;
import com.az.edadi.dal.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "post")
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity {

    private String content;
    private String userId;
    private PostParentType parentType;
    private String parentId;
    private Integer likeCount;
    private Integer commentCount;
}
