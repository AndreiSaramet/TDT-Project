package com.ubb.mai.backend.Repository;

import com.ubb.mai.backend.Model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<CourseRegistration, Long> {
    List<CourseRegistration> findCourseRegistrationByStudent_Username(String username);

    CourseRegistration findByCourseIdAndStudentId(Long courseId, Long studentId);
}
