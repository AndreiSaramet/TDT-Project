package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
