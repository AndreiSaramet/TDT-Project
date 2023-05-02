package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
