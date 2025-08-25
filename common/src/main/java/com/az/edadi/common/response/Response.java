package com.az.edadi.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @JsonInclude
    private int httpCode;

    @JsonInclude
    private String code;

    @JsonInclude
    private String userMessage;

    @JsonInclude
    private String technicalMessage;

}
