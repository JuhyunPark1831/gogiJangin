package com.project.gogiJangin.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.gogiJangin.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"code", "message", "data"})
public class CustomResponseEntity<T> {

    private final int httpStatus;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public CustomResponseEntity(int httpStatus, String code, String message, T data) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CustomResponseEntity<T> success(String successMessage, T data) {
        return new CustomResponseEntity<>(HttpStatus.OK.value(), null, successMessage, data);
    }

    public static CustomResponseEntity<?> error(String errorMessage, ErrorCode errorCode) {
        return new CustomResponseEntity<>(errorCode.getHttpStatus(), errorCode.getCode(), errorMessage, null);
    }
}
