package com.az.edadi.dal.entity.speciality;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "speciality")
public class Speciality {
    @Id
    private String id;
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
