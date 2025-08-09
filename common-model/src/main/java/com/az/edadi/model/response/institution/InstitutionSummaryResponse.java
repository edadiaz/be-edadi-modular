package com.az.edadi.model.response.institution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionSummaryResponse {
    private String id;
    private String abbr;
    private String name;
    private String photoUrl;
}
