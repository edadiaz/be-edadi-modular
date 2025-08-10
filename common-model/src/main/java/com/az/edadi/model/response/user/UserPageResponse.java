package com.az.edadi.model.response.user;

import com.az.edadi.model.constant.PageTab;
import com.az.edadi.model.response.institution.InstitutionSummaryResponse;
import com.az.edadi.model.response.speciality.SpecialitySummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
