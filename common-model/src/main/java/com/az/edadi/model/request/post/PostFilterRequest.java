package com.az.edadi.model.request.post;

import com.az.edadi.common.constant.post.PostParentType;
import com.az.edadi.common.constant.SortDirection;
import com.az.edadi.common.constant.post.PostSortColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFilterRequest {

    private String postParentId;
    private PostParentType parentType;
    private Integer page;
    private Integer size;
    private PostSortColumn sortColumn;
    private SortDirection sortDirection;
}
