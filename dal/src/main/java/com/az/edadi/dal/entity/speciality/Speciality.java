package com.az.edadi.dal.entity.speciality;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Getter
@Setter
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
