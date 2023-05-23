package com.auctix.auctx.controller;

import com.auctix.auctx.converter.UserProfileConverter;
import com.auctix.auctx.dto.UserProfileDto;
import com.auctix.auctx.exception.UserProfileNotFoundException;
import com.auctix.auctx.model.UserProfile;
import com.auctix.auctx.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileController {
    private final UserProfileService userProfileService;

    private final UserProfileConverter userProfileConverter;

    @PostMapping(path = "/addUserProfile/{userId}")
    public UserProfileDto addUserProfile(@PathVariable(name = "userId") Long userId, @RequestParam(name = "profilePicture", required = false) MultipartFile multipartFile, @RequestParam String bio) throws IOException {
        UserProfile userProfile = this.userProfileService.findByUserId(userId);
        UserProfileDto userProfileDto;
        if (userProfile == null) {
            throw new UserProfileNotFoundException();
        }
        else if (multipartFile == null) {
            userProfileDto = UserProfileDto.builder()
                    .id(userId)
                    .bio(bio)
                    .profilePicture(userProfile.getProfilePicture())
                    .build();
        }
        else {
            userProfileDto = UserProfileDto.builder()
                    .id(userId)
                    .bio(bio)
                    .profilePicture(multipartFile.getBytes())
                    .build();
        }
        return this.userProfileConverter.convertModelToDto(this.userProfileService.save(this.userProfileConverter.convertDtoToModel(userProfileDto)));
    }

    @PutMapping(path = "/updateUserProfile/{userId}")
    public UserProfileDto updateUserProfile(@PathVariable(name = "userId") Long userId, @RequestParam(name = "profilePicture") MultipartFile multipartFile, @RequestParam String bio) throws IOException {
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .id(userId)
                .bio(bio)
                .profilePicture(multipartFile.getBytes())
                .build();
        return this.userProfileConverter.convertModelToDto(this.userProfileService.update(this.userProfileConverter.convertDtoToModel(userProfileDto)));
    }

    @GetMapping(path = "/findUserProfileByUserId/{userId}")
    public UserProfileDto findUserProfileByUserId(@PathVariable(name = "userId") Long userId) {
        return this.userProfileConverter.convertModelToDto(this.userProfileService.findByUserId(userId));
    }

    @DeleteMapping(path = "/deleteUserProfile/{userId}")
    public void deleteUserProfile(@PathVariable(name = "userId") Long userId) {
        this.userProfileService.deleteById(userId);
    }

    @GetMapping(path = "/findUserProfileByUsername/{username}")
    public UserProfileDto findUserProfileByUsername(@PathVariable(name = "username") String username) {
        return this.userProfileConverter.convertModelToDto(this.userProfileService.findByUsername(username));
    }
}
