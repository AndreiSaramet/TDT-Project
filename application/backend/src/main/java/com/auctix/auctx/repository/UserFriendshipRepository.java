package com.auctix.auctx.repository;

import com.auctix.auctx.model.UserFriendship;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserFriendshipRepository extends JpaRepository<UserFriendship, Long> {
    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByUserIdAndFriendId(Long userId, Long friendId);

    @Transactional
    void delete(UserFriendship userFriendship);

    Optional<UserFriendship> findByUserIdAndFriendId(Long userId, Long friendId);
}
