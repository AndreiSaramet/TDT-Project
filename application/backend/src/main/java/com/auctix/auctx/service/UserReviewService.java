package com.auctix.auctx.service;

import com.auctix.auctx.exception.UserReviewAlreadyExistingException;
import com.auctix.auctx.exception.UserReviewNotFoundException;
import com.auctix.auctx.model.UserReview;
import com.auctix.auctx.repository.UserReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserReviewService implements IUserReviewService {
    private final UserReviewRepository userReviewRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserReviewService.class);

    @Override
    public UserReview save(UserReview userReview) {
        logger.info(String.format("save: %d", userReview.getId()));
        return this.userReviewRepository.save(userReview);
    }

    @Override
    public void deleteById(Long id) {
        logger.info(String.format("deleteById: %d", id));
        this.userReviewRepository.findById(id).orElseThrow(UserReviewNotFoundException::new);
        this.userReviewRepository.deleteById(id);
    }

    @Override
    public UserReview findById(Long id) {
        logger.info(String.format("findById: %d", id));
        return this.userReviewRepository.findById(id).orElseThrow(UserReviewNotFoundException::new);
    }

    @Override
    public UserReview update(UserReview userReview) {
        logger.info(String.format("update: %d", userReview.getId()));
        this.userReviewRepository.findById(userReview.getId()).orElseThrow(UserReviewNotFoundException::new);
        return this.userReviewRepository.save(userReview);
    }

    @Override
    public List<UserReview> findByRating(Integer rating) {
        logger.info(String.format("findByRating: %d", rating));
        return this.userReviewRepository.findAllByRating(rating);
    }

    @Override
    public List<UserReview> findAllOrderByRatingAsc() {
        logger.info("findAllOrderByRatingAsc");
        return this.userReviewRepository.findAllByOrderByRatingAsc();
    }

    @Override
    public List<UserReview> findAllOrderByRatingDesc() {
        logger.info("findAllOrderByRatingDesc");
        return this.userReviewRepository.findAllByOrderByRatingDesc();
    }

    @Override
    public List<UserReview> findByDateBefore(LocalDateTime beforeDate) {
        logger.info(String.format("findByDateBefore: %s", beforeDate));
        return this.userReviewRepository.findAllByPostDateBefore(beforeDate);
    }

    @Override
    public List<UserReview> findByDateAfter(LocalDateTime afterDate) {
        logger.info(String.format("findByDateAfter: %s", afterDate));
        return this.userReviewRepository.findAllByPostDateAfter(afterDate);
    }

    @Override
    public List<UserReview> findByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        logger.info(String.format("findByDateInterval: %s to %s", startDate, endDate));
        return this.userReviewRepository.findAllByPostDateBetween(startDate, endDate);
    }
}
