package com.auctix.auctx.service;

import com.auctix.auctx.model.UserProfile;

public interface IUserProfileService {
    UserProfile save(UserProfile userProfile);

    void deleteById(Long id);

    UserProfile findByUserId(Long id);

    UserProfile update(UserProfile userProfile);

    UserProfile findByUsername(String username);
}
