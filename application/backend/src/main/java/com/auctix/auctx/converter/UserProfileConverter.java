package com.auctix.auctx.converter;

import com.auctix.auctx.dto.UserProfileDto;
import com.auctix.auctx.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileConverter implements IConverter<UserProfile, UserProfileDto> {
    @Override
    public UserProfile convertDtoToModel(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDto.getId());
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setProfilePicture(userProfileDto.getProfilePicture());
        return userProfile;
    }

    @Override
    public UserProfileDto convertModelToDto(UserProfile userProfile) {
        if (userProfile == null) {
            return new UserProfileDto();
        }
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(userProfile.getId());
        userProfileDto.setBio(userProfile.getBio());
        userProfileDto.setProfilePicture(userProfile.getProfilePicture());
        return userProfileDto;
    }

    @Override
    public List<UserProfileDto> convertModelListToDtoList(List<UserProfile> userProfiles) {
        return userProfiles.stream()
                .map(this::convertModelToDto)
                .toList();
    }
}
