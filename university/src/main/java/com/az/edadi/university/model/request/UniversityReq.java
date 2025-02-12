package com.az.edadi.university.model.request;

import com.az.edadi.dal.constants.UniversityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityReq {
    private String abbrAz;
    private String abbrEn;
    private String nameAz;
    private String nameEn;
    private String photoUrl;
    private Integer foundedYear;
    private String webSiteUrl;
    private Integer countOfStudent;
    private String  type;
    private String description;
    private String location;
}
