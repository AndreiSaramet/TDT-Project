package com.auctix.auctx.converter;

import com.auctix.auctx.dto.UserFriendshipDto;
import com.auctix.auctx.model.UserFriendship;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFriendshipConverter implements IConverter<UserFriendship, UserFriendshipDto>{

    @Override
    public UserFriendship convertDtoToModel(UserFriendshipDto userFriendshipDto) {
        Users user = new Users();
        user.setId(userFriendshipDto.getUserId());

        Users friend = new Users();
        friend.setId(userFriendshipDto.getFriendId());

        return UserFriendship.builder()
                .id(userFriendshipDto.getId())
                .acceptDate(userFriendshipDto.getAcceptDate())
                .user(user)
                .friend(friend)
                .build();
    }

    @Override
    public UserFriendshipDto convertModelToDto(UserFriendship userFriendship) {
        if (userFriendship == null) {
            return new UserFriendshipDto();
        }

        return UserFriendshipDto.builder()
                .id(userFriendship.getId())
                .acceptDate(userFriendship.getAcceptDate())
                .userId(userFriendship.getUser().getId())
                .friendId(userFriendship.getFriend().getId())
                .build();
    }

    @Override
    public List<UserFriendshipDto> convertModelListToDtoList(List<UserFriendship> userFriendships) {
        return userFriendships.stream()
                .map(this::convertModelToDto)
                .toList();
    }
}
