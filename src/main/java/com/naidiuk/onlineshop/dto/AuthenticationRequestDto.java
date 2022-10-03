package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
