package com.project.gogiJangin.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    TEST_ERROR(400, "9999", "테스트 에러 처리");

    private final int httpStatus;
    private final String code;
    private final String message;
}