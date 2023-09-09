package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

//token 2000이상
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{
    INVALID_TOKEN(400,2000,"Invalid token"),
    EXPIRED_TOKEN(400,2001,"Expired token"),
    TOKEN_EXCEPTION(400,2002,"Token error"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400,2003,"Authorization token not found"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
