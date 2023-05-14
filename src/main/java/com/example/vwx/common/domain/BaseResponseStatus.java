package com.example.vwx.common.domain;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, 200, "요청에 성공하였습니다."),

    RESPONSE_ERROR(false, 3000, "해당 키워드에 대한 검색 결과가 없습니다."),
    UNAUTHORIZED(false, 3010, "회원가입 되지 않은 유저입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
