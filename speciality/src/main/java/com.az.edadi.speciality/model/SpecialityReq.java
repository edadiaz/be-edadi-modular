package com.az.edadi.speciality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityReq {
    private Long specialityCode;
    private String specialityName;
    private String institutionId;
    private String specialityGroup;
    private String studyLang;
    private String city;
    private String studyType;
    private double tuitionFee;
    private String selfFundedPassingScore;
    private String stateFundedPassingScore;
    private String specialityUrl;
    private String currency;
}
