package com.catalogapp.securityservicejwt;

import com.catalogapp.securityservicejwt.exception.UserException;
import com.catalogapp.securityservicejwt.validation.ValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHanlder extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value
                = { UserException.class})
        protected ResponseEntity<Object> handleConflict(
                UserException ex, WebRequest request) {
            var bodyOfResponse = new ValidationResponse(ex.getMessage());
            return ResponseEntity.status(ex.getCode()).body(bodyOfResponse);
        }

}
