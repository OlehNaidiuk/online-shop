package com.naidiuk.onlineshop.security;

import com.naidiuk.onlineshop.dto.AuthenticationDto;
import com.naidiuk.onlineshop.dto.AuthenticationRequestDto;
import com.naidiuk.onlineshop.entity.User;
import com.naidiuk.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationDto getAuthentication(AuthenticationRequestDto authRequestDto) {
        User user = userService.findByUsername(authRequestDto.getUsername());
        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        authRequestDto.getUsername(), authRequestDto.getPassword()));
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());

        return AuthenticationDto.builder()
                                .username(user.getUsername())
                                .token(token)
                                .build();
    }
}
