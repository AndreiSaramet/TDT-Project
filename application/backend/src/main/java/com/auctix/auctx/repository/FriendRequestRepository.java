package com.auctix.auctx.repository;

import com.auctix.auctx.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteBySenderIdAndReceiverId(Long senderId, Long receiverId);

    List<FriendRequest> findAllBySenderId(Long senderId);

    List<FriendRequest> findAllByReceiverId(Long receiverId);

    Optional<FriendRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    @Transactional
    void delete(FriendRequest friendRequest);
}
