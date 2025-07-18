package com.az.edadi.model.response.university;

import com.az.edadi.model.constant.PageTab;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityPageResponse {
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
