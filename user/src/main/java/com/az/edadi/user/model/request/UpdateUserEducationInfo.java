package com.az.edadi.user.model.request;

import com.az.edadi.common_model.constant.EducationDegree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserEducationInfo {
    private EducationDegree degree;
    private String universityId;
    private String specialityId;
}
