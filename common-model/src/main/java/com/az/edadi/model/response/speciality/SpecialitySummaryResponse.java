package com.az.edadi.model.response.speciality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialitySummaryResponse {
    private UUID id;
    private String name;
    private String code;
    private String institutionId;
}
