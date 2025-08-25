package com.az.edadi.common.response;

import java.io.Serializable;

public interface ResponseMessage extends Serializable {

    String getMessageCode();

    String getCode();

}