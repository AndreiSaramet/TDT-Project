package com.auctix.auctx.controller;

import com.auctix.auctx.converter.UserConverter;
import com.auctix.auctx.converter.UserFriendshipConverter;
import com.auctix.auctx.dto.UsersDto;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.service.UserFriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFriendshipController {

    private final UserFriendshipService userFriendshipService;

    private final UserFriendshipConverter userFriendshipConverter;

    private final UserConverter userConverter;

    @PostMapping("/addFriend/{userId}/{friendId}")
    public void addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        userFriendshipService.addFriend(userId, friendId);
    }

    @DeleteMapping("/removeFriend/{userId}/{friendId}")
    public void removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        userFriendshipService.removeFriend(userId, friendId);
    }

    @GetMapping("/findAllFriends/{userId}")
    public List<UsersDto> findAllFriends(@PathVariable Long userId) {
        List<Users> friends = userFriendshipService.findAllFriends(userId);
        return userConverter.convertModelListToDtoList(friends);
    }

    @GetMapping("/isFriend/{userId}/{friendId}")
    public Boolean isFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        return userFriendshipService.isFriend(userId, friendId);
    }
}
