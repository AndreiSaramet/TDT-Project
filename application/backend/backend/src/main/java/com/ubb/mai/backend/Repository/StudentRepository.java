package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

    List<Student> findByGroup(Long group);

    List<Student> findByCurrentYear(Integer year, Sort sort);
}
