package com.naidiuk.onlineshop.controller.handler;

import com.naidiuk.onlineshop.controller.error.ApplicationError;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApplicationError> handleCompanyNotFoundException(CompanyNotFoundException e) {
        ApplicationError applicationError = new ApplicationError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(applicationError);
    }
}
