package com.auctix.auctx.service;

import com.auctix.auctx.model.UserReview;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserReviewService {
    UserReview save(UserReview userReview);

    void deleteById(Long id);

    UserReview findById(Long id);

    UserReview update(UserReview userReview);

    List<UserReview> findByRating(Integer rating);

    List<UserReview> findAllOrderByRatingAsc();

    List<UserReview> findAllOrderByRatingDesc();

    List<UserReview> findByDateAfter(LocalDateTime afterDate);

    List<UserReview> findByDateBefore(LocalDateTime beforeDate);

    List<UserReview> findByDateInterval(LocalDateTime startDate, LocalDateTime endDate);
}
