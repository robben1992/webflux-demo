package org.ly.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@Order(-2)
@RestControllerAdvice(basePackages = "org.ly.controller")
public class ControllerExceptionHandler {
    /**
     * BusinessException handler
     */
    // @ResponseBody
    // @ExceptionHandler(value = BusinessException.class)
    // public ResponseEntity<Object> businessExceptionHandler(BusinessException e) {
    //     log.info("[BusinessException]ErrorCode:{}, ErrorMsg:{}", e.getErrorCode(), e.getErrorMsg());
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMsg());
    // }

    /**
     * WebExchangeBindException handler
     */
    @ResponseBody
    @ExceptionHandler(value = WebExchangeBindException.class)
    public ResponseEntity<Object> webExchangeBindExceptionHandler(WebExchangeBindException e) {
        log.info("[WebExchangeBindException]", e);

        var fieldError = e.getBindingResult().getFieldError();
        if(fieldError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCodeEnums.INVALID_PARAMETER);
    }

    /**
     * ConstraintViolationException handler
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.info("[ConstraintViolationException]", e);
        if (!CollectionUtils.isEmpty(e.getConstraintViolations())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCodeEnums.INVALID_PARAMETER);
    }
}
