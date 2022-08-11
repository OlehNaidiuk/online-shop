package com.naidiuk.onlineshop.controller.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorReport {
    private int statusCode;
    private String Message;
}
