package com.project.gogiJangin.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    TEST_ERROR(400, "9999", "테스트 에러 처리"),

    NOT_FOUND_FRANCHISE(400, "200", "잘못된 가맹문의입니다"),

    NOT_FOUND_REGION(400, "400", "잘못된 지역입니다");

    private final int httpStatus;
    private final String code;
    private final String message;
}