package com.auctix.auctx.controller;

import com.auctix.auctx.converter.FriendRequestConverter;
import com.auctix.auctx.dto.FriendRequestDto;
import com.auctix.auctx.model.FriendRequest;
import com.auctix.auctx.service.FriendRequestService;
import com.auctix.auctx.service.UserFriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    private final FriendRequestConverter friendRequestConverter;

    private final UserFriendshipService userFriendshipService;

    @PostMapping("/sendFriendRequest/{senderId}/{receiverId}")
    public void sendFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        friendRequestService.sendFriendRequest(senderId, receiverId);
    }

    @PostMapping("/acceptFriendRequest/{senderId}/{receiverId}")
    public void acceptFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        friendRequestService.removeFriendRequest(senderId, receiverId);
        userFriendshipService.addFriend(senderId, receiverId);
    }

    @DeleteMapping("/removeFriendRequest/{senderId}/{receiverId}")
    public void removeFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        friendRequestService.removeFriendRequest(senderId, receiverId);
    }

    @GetMapping("/findFriendRequestBySenderAndReceiver/{senderId}/{receiverId}")
    public FriendRequestDto findFriendRequestBySenderAndReceiver(@PathVariable Long senderId, @PathVariable Long receiverId) {
        FriendRequest friendRequest = friendRequestService.findFriendRequestBySenderIdAndReceiverId(senderId, receiverId);
        return friendRequestConverter.convertModelToDto(friendRequest);
    }

    @GetMapping("/findAllFriendRequestsBySender/{senderId}")
    public List<FriendRequestDto> findAllFriendRequestsBySender(@PathVariable Long senderId) {
        List<FriendRequest> friendRequests = friendRequestService.findAllFriendRequestsBySenderId(senderId);
        return friendRequestConverter.convertModelListToDtoList(friendRequests);
    }

    @GetMapping("/findAllFriendRequestsByReceiver/{receiverId}")
    public List<FriendRequestDto> findAllFriendRequestsByReceiver(@PathVariable Long receiverId) {
        List<FriendRequest> friendRequests = friendRequestService.findAllFriendRequestsByReceiverId(receiverId);
        return friendRequestConverter.convertModelListToDtoList(friendRequests);
    }
}
