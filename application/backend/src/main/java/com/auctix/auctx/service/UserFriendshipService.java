package com.auctix.auctx.service;

import com.auctix.auctx.exception.OwnFriendRequestException;
import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.exception.UsersNotFriendsException;
import com.auctix.auctx.model.UserFriendship;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UserFriendshipRepository;
import com.auctix.auctx.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFriendshipService implements IUserFriendshipService {

    private final UserFriendshipRepository userFriendshipRepository;

    private final UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserFriendshipService.class);
    @Override
    public void addFriend(Long userId, Long friendId) {
        logger.info("Adding friend with id: " + friendId + " to user with id: " + userId);
        if(userId.equals(friendId)){
            throw new OwnFriendRequestException();
        }

        //swap the values since we save the lower id first
        if (userId > friendId)
            userId = userId ^ friendId ^ (friendId = userId);

        Users user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserFriendship userFriendship = new UserFriendship();
        userFriendship.setUser(user);

        Users friend = usersRepository.findById(friendId).orElseThrow(UserNotFoundException::new);
        userFriendship.setFriend(friend);

        userFriendship.setAcceptDate(LocalDateTime.now());

        userFriendshipRepository.save(userFriendship);
    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        logger.info("Removing friend with id: " + friendId + " from user with id: " + userId);

        //swap the values since they were saved in ascending order of id
        if (userId > friendId)
            userId = userId ^ friendId ^ (friendId = userId);

        UserFriendship userFriendship = userFriendshipRepository.findByUserIdAndFriendId(userId, friendId).orElseThrow(UsersNotFriendsException::new);

        userFriendshipRepository.delete(userFriendship);
    }

    @Override
    public List<Users> findAllFriends(Long userId) {
        logger.info("Finding all friends of user with id: " + userId);

        Users user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<Users> friends = new ArrayList<>(user.getUserFriendships()
                .stream()
                .map(UserFriendship::getFriend)
                .toList());

        List<Users> indirectFriends = user.getFriendFriendships()
                .stream()
                .map(UserFriendship::getUser)
                .toList();

        friends.addAll(indirectFriends);
        return friends;
    }

    @Override
    public Boolean isFriend(Long userId, Long friendId) {
        logger.info("Checking if user with id: " + userId + " is friend with user with id: " + friendId);

        if(userId>friendId)
            userId = userId ^ friendId ^ (friendId = userId);
        return userFriendshipRepository.findByUserIdAndFriendId(userId, friendId).isPresent();
    }

    @Override
    public List<LocalDateTime> findAllAddedAtDates(Long userId) {
        logger.info("Finding all dates for user : " + userId);

        Users user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<LocalDateTime> dates = new ArrayList<>(user.getUserFriendships()
                .stream()
                .map(UserFriendship::getAcceptDate)
                .toList());

        List<LocalDateTime> indirectDates = user.getFriendFriendships()
                .stream()
                .map(UserFriendship::getAcceptDate)
                .toList();

        dates.addAll(indirectDates);
        return dates;
    }
}
