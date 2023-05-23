package com.auctix.auctx.service;

import com.auctix.auctx.model.FriendRequest;
import com.auctix.auctx.model.Users;

import java.util.List;

public interface IFriendRequestService {
    void sendFriendRequest(Long senderId, Long receiverId);
    void removeFriendRequest(Long senderId, Long receiverId);

    FriendRequest findFriendRequestBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<FriendRequest> findAllFriendRequestsBySenderId(Long senderId);
    List<FriendRequest> findAllFriendRequestsByReceiverId(Long receiverId);

    List<Users> findAllUserSendersByReceiverId(Long receiverId);

    List<Users> findAllUserReceiversBySenderId(Long senderId);
}
