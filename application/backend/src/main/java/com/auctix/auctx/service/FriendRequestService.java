package com.auctix.auctx.service;

import com.auctix.auctx.exception.*;
import com.auctix.auctx.model.FriendRequest;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.FriendRequestRepository;
import com.auctix.auctx.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FriendRequestService implements IFriendRequestService{

    private final FriendRequestRepository friendRequestRepository;

    private final UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(FriendRequestService.class);

    @Override
    public void sendFriendRequest(Long senderId, Long receiverId) {
        logger.info("Friend request from: " + senderId + " to user with id: " + receiverId);
        if(senderId.equals(receiverId)){
            throw new OwnFriendRequestException();
        }

        Users sender = usersRepository.findById(senderId).orElseThrow(SenderNotFoundException::new);
        Users receiver = usersRepository.findById(receiverId).orElseThrow(ReceiverNotFoundException::new);


        friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId).ifPresent(friendRequest -> {
            throw new FriendRequestAlreadyExistingException();
        });

        friendRequestRepository.findBySenderIdAndReceiverId(receiverId, senderId).ifPresent(friendRequest -> {
            throw new FriendRequestAlreadyExistingException();
        });

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setSendDate(LocalDateTime.now());
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void removeFriendRequest(Long senderId, Long receiverId) {
        logger.info("Deleting friend request from id: " + senderId + " to id: " + receiverId);
        FriendRequest friendRequest = friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId).orElseThrow(FriendRequestNotFoundException::new);
        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public FriendRequest findFriendRequestBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        logger.info("Finding friend request from id: " + senderId + " to id: " + receiverId);
        return friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId).orElseThrow(FriendRequestNotFoundException::new);
    }

    @Override
    public List<FriendRequest> findAllFriendRequestsBySenderId(Long senderId) {
        logger.info("Finding all friend requests sent by user with id: " + senderId);
        return usersRepository.findById(senderId).orElseThrow(SenderNotFoundException::new).getSentFriendRequests();
    }

    @Override
    public List<FriendRequest> findAllFriendRequestsByReceiverId(Long receiverId) {
        logger.info("Finding all friend requests received by user with id: " + receiverId);
        return usersRepository.findById(receiverId).orElseThrow(ReceiverNotFoundException::new).getReceivedFriendRequests();
    }

    @Override
    public List<Users> findAllUserSendersByReceiverId(Long receiverId) {
        logger.info("Finding all senders of friend requests received by user with id: " + receiverId);
        return usersRepository
                .findById(receiverId)
                .orElseThrow(SenderNotFoundException::new)
                .getReceivedFriendRequests()
                .stream()
                .map(FriendRequest::getSender)
                .toList();
    }

    @Override
    public List<Users> findAllUserReceiversBySenderId(Long senderId) {
        logger.info("Finding all receivers of friend requests sent by user with id: " + senderId);
        return usersRepository
                .findById(senderId)
                .orElseThrow(SenderNotFoundException::new)
                .getSentFriendRequests()
                .stream()
                .map(FriendRequest::getReceiver)
                .toList();
    }
}
