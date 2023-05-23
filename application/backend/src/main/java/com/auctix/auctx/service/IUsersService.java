package com.auctix.auctx.service;

import com.auctix.auctx.model.Users;

public interface IUsersService {
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users save(Users users);
    void deleteByEmail(String email);
    Users findById(Long id);
    Users update(Users users);

    void deleteById(Long id);
}
