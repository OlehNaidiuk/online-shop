package com.naidiuk.onlineshop.security;

import com.naidiuk.onlineshop.dto.AuthenticationUserDto;
import com.naidiuk.onlineshop.dto.AuthenticationRequestDto;

public interface SecurityService {
    AuthenticationUserDto getAuthentication(AuthenticationRequestDto authRequestDto);
}
