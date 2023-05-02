package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Disciplines, Long> {
    Disciplines findByName(String username);
}
