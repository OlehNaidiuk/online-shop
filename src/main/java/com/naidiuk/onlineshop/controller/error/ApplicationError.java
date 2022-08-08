package com.naidiuk.onlineshop.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationError {
    private int statusCode;
    private String Message;
}
