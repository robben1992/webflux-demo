package org.ly.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCodeEnums {
    SUCCESS(0,"success"),

    ERROR(500,"system error"),

    REQUEST_ERROR(400,"request error"),

    UNAUTHORIZED(401,"Unauthorized"),

    FORBIDDEN(403,"not have the required permissions to access"),

    REQUEST_TIMEOUT(408,"request timeout"),

    TOO_MANY_REQUESTS(429,"access limit error"),

    /************   nft-service business error code 200320001 ~  200320999**************/

    INVALID_PARAMETER(200320001,"invalid parameter error"),

    INVALID_IP(200320002,"this ip already submitted within 24 hours"),

    INVALID_STATUS(200320003,"either id does not exist or status must be disable to allow edit"),

    REPEATED_SLUG(200320004, "exists the same slug"),

    DATA_NOT_EXIST(200320005, "data not exists, please check");

    private final Integer code;

    private final String message;
}
