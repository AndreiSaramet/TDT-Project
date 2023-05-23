package com.auctix.auctx.repository;

import com.auctix.auctx.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    void deleteById(Long id);

}
