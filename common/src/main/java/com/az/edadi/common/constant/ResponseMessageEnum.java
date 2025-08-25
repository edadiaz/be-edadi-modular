package com.az.edadi.common.constant;


import com.az.edadi.common.response.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessageEnum implements ResponseMessage {

    OK("success.ok");

    private final String messageCode;

    @Override
    public String getCode() {
        return name();
    }

}