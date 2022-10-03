package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.entity.User;

public interface UserService {
    User findByUsername(String username);
}
