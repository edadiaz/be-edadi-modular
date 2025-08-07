package com.az.edadi.model.response.user;

import com.az.edadi.model.constant.PageTab;
import com.az.edadi.model.response.institution.InstitutionSummaryResponse;
import com.az.edadi.model.response.speciality.SpecialitySummaryResponse;
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
    private InstitutionSummaryResponse institution;
    private SpecialitySummaryResponse speciality;
    private List<PageTab> pageTabs;
}
