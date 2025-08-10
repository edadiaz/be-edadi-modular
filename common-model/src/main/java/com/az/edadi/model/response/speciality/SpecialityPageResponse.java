package com.az.edadi.model.response.speciality;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityPageResponse {
    private String id;
    private Long specialityCode;
    private String specialityName;
    private String institutionId;
    private String specialityGroup;
    private String specialitySubGroup;
    private String studyLang;
    private String city;
    private String studyType;
    private String tuitionFee;
    private String selfFundedPassingScore;
    private String stateFundedPassingScore;
    private String specialityUrl;
    private String currency;
}
