package com.az.edadi.model.response.institution;

import com.az.edadi.common.constant.PageTab;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionPageResponse {
    private String id;
    private String abbr;
    private String name;
    private String photoUrl;
    private Integer foundedYear;
    private String webSiteUrl;
    private Integer countOfStudent;
    private String type;
    private String description;
    private String location;
    private List<PageTab> tabList;

}
