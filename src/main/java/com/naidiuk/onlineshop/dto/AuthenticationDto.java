package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationDto {
    private String username;
    private String token;
}
