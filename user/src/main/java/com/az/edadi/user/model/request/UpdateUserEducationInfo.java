package com.az.edadi.user.model.request;

import com.az.edadi.dal.types.AcademicDegree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserEducationInfo {
    private AcademicDegree degree;
    private UUID universityId;
    private UUID specialityId;
}
