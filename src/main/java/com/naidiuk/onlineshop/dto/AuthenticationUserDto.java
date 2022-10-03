package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationUserDto {
    private String username;
    private String token;
}
