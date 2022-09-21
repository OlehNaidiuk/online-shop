package com.naidiuk.onlineshop.handler;

import com.naidiuk.onlineshop.controller.response.ErrorResponse;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.error.ProductNotFoundException;
import com.naidiuk.onlineshop.error.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CompanyNotFoundException.class,
            CategoryNotFoundException.class,
            ProductNotFoundException.class,
            ReviewNotFoundException.class,
            UsernameNotFoundException.class,
            AuthenticationException.class
    })
    public ResponseEntity<ErrorResponse> handle(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
