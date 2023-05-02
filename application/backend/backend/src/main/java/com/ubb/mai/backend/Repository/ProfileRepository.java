package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
