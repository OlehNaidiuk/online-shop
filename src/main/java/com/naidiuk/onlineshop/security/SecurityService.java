package com.naidiuk.onlineshop.security;

import com.naidiuk.onlineshop.dto.AuthenticationDto;
import com.naidiuk.onlineshop.dto.AuthenticationRequestDto;

public interface SecurityService {
    AuthenticationDto getAuthentication(AuthenticationRequestDto authRequestDto);
}
