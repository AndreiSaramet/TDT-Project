package com.auctix.auctx.repository;

import com.auctix.auctx.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    List<UserReview> findAllByRating(Integer rating);

    List<UserReview> findAllByOrderByRatingAsc();

    List<UserReview> findAllByOrderByRatingDesc();

    List<UserReview> findAllByPostDateAfter(LocalDateTime afterDate);

    List<UserReview> findAllByPostDateBefore(LocalDateTime beforeDate);

    List<UserReview> findAllByPostDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
