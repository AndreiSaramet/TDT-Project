package com.auctix.auctx.service;

import com.auctix.auctx.model.Users;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserFriendshipService {
    void addFriend(Long userId, Long friendId);

    void removeFriend(Long userId, Long friendId);

    List<Users> findAllFriends(Long userId);

    Boolean isFriend(Long userId, Long friendId);

    List<LocalDateTime> findAllAddedAtDates(Long userId);
}
