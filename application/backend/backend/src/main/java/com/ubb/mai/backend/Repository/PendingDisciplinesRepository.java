package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.PendingDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingDisciplinesRepository extends JpaRepository<PendingDiscipline, Long> {
    List<PendingDiscipline> getPendingDisciplinesByTeacher_Username(String teacher);
}
