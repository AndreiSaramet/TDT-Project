package com.auctix.auctx.converter;

import com.auctix.auctx.dto.FriendRequestDto;
import com.auctix.auctx.model.FriendRequest;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendRequestConverter implements IConverter<FriendRequest, FriendRequestDto> {
    @Override
    public FriendRequest convertDtoToModel(FriendRequestDto friendRequestDto) {
        Users sender = new Users();
        sender.setId(friendRequestDto.getSenderId());

        Users receiver = new Users();
        receiver.setId(friendRequestDto.getReceiverId());

        return FriendRequest.builder()
                .id(friendRequestDto.getId())
                .sendDate(friendRequestDto.getSendDate())
                .sender(sender)
                .receiver(receiver)
                .build();
    }

    @Override
    public FriendRequestDto convertModelToDto(FriendRequest friendRequest) {
        if (friendRequest == null) {
            return new FriendRequestDto();
        }

        return FriendRequestDto.builder()
                .id(friendRequest.getId())
                .sendDate(friendRequest.getSendDate())
                .senderId(friendRequest.getSender().getId())
                .receiverId(friendRequest.getReceiver().getId())
                .build();
    }

    @Override
    public List<FriendRequestDto> convertModelListToDtoList(List<FriendRequest> friendRequests) {
        return friendRequests.stream()
                .map(this::convertModelToDto)
                .toList();
    }
}
