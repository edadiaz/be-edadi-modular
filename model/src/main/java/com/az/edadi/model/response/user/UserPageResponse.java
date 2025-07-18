package com.az.edadi.model.response.user;

import com.az.edadi.model.constant.PageTab;
import lombok.Builder;

import java.util.List;
@Builder
public class UserPageResponse {
    private String id;
    private String username;
    private String fullName;
    private String profileImageUrl;
    private String specialityId;
    private Boolean isCurrentUser;
    private List<PageTab> pageTabs;
}
