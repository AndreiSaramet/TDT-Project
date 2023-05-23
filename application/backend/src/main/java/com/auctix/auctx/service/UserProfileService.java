package com.auctix.auctx.service;

import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.exception.UserProfileNotFoundException;
import com.auctix.auctx.model.UserProfile;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UserProfileRepository;
import com.auctix.auctx.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileService implements IUserProfileService {
    private final UserProfileRepository userProfileRepository;

    private final UsersRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Override
    public UserProfile save(UserProfile userProfile) {
        logger.info(String.format("Saving user profile with id %d", userProfile.getId()));
        Users user = this.userRepository.findById(userProfile.getId()).orElseThrow(UserNotFoundException::new);
        this.userRepository.save(user);
        userProfile.setUser(user);
        return this.userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteById(Long id) {
        logger.info(String.format("Deleting user profile with id %d", id));
        this.userProfileRepository.findById(id).orElseThrow(UserProfileNotFoundException::new);
        this.userProfileRepository.deleteById(id);
    }

    @Override
    public UserProfile findByUserId(Long id) {
        return this.userProfileRepository.findByUserId(id);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        logger.info(String.format("Updating user profile with id %d", userProfile.getId()));
        Users user = this.userRepository.findById(userProfile.getId()).orElseThrow(UserNotFoundException::new);
        this.userProfileRepository.findById(userProfile.getId()).orElseThrow(UserProfileNotFoundException::new);
        userProfile.setUser(user);
        return this.userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findByUsername(String username) {
        logger.info(String.format("Finding user profile with username %s", username));
        Users user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return this.userProfileRepository.findByUserId(user.getId());
    }
}
