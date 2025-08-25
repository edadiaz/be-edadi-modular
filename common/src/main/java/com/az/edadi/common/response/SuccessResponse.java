package com.az.edadi.common.response;

import com.az.edadi.common.component.MessageResolver;
import com.az.edadi.common.constant.ResponseMessageEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<R> {

    @JsonInclude
    private Response response;

    @ToString.Exclude
    private R result;

    public static <R> SuccessResponse<R> fetch(R result) {
        return fetch().result(result);
    }

    public static SuccessBuilder fetch() {
        return new SuccessBuilder(HttpStatus.OK, ResponseMessageEnum.OK);
    }

    public static SuccessBuilder ok() {
        return new SuccessBuilder(HttpStatus.OK, ResponseMessageEnum.OK);
    }

    public static SuccessMessageBuilder ok(MessageResolver messageResolver) {
        return new SuccessMessageBuilder(HttpStatus.OK, messageResolver).responseMessage(ResponseMessageEnum.OK);
    }

    public static SuccessMessageBuilder created(MessageResolver messageResolver) {
        return new SuccessMessageBuilder(HttpStatus.CREATED, messageResolver).responseMessage(ResponseMessageEnum.OK);
    }

    public static SuccessMessageBuilder accepted(MessageResolver messageResolver) {
        return new SuccessMessageBuilder(HttpStatus.ACCEPTED, messageResolver).responseMessage(ResponseMessageEnum.OK);
    }

    @RequiredArgsConstructor
    public static class SuccessBuilder {

        private final HttpStatus httpStatus;
        private final ResponseMessage responseMessage;

        public <R> SuccessResponse<R> build() {
            return result(null);
        }

        public <R> SuccessResponse<R> result(R result) {
            Response response = Response.builder()
                    .httpCode(httpStatus.value())
                    .code(responseMessage.getCode())
                    .userMessage(responseMessage.getMessageCode())
                    .build();

            return new SuccessResponse<>(response, result);
        }
    }

    @RequiredArgsConstructor
    public static class SuccessMessageBuilder {

        private final HttpStatus httpStatus;
        private final MessageResolver messageResolver;
        private ResponseMessage responseMessage;
        private Object[] messageObjects;

        public SuccessMessageBuilder responseMessage(ResponseMessage responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public SuccessMessageBuilder messageObjects(Object[] messageObjects) {
            this.messageObjects = messageObjects;
            return this;
        }

        public <R> SuccessResponse<R> build() {
            return result(null);
        }

        public <R> SuccessResponse<R> result(@Nullable R result) {
            Assert.notNull(responseMessage, "Message must not be null");
            Response response = Response.builder()
                    .httpCode(httpStatus.value())
                    .code(responseMessage.getCode())
                    .userMessage(messageResolver.resolve(responseMessage.getMessageCode(), messageObjects))
                    .build();

            return new SuccessResponse<>(response, result);
        }
    }

}
