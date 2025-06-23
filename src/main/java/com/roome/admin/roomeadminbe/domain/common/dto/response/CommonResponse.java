package com.roome.admin.roomeadminbe.domain.common.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {

    T data;

    public static <T> CommonResponse<T> ofData(T data) {
        return CommonResponse.<T>builder()
            .data(data)
            .build();
    }

    public static <T> ResponseEntity<CommonResponse<T>> ofDataWithHttpStatus(T data, HttpStatus httpStatus) {
        CommonResponse<T> commonResponse = CommonResponse.ofData(data);
        return ResponseEntity.status(httpStatus).body(commonResponse);
    }
}
