package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);

    List<Teacher> findAll();
}
