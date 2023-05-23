package com.auctix.auctx.service;

import com.auctix.auctx.exception.ProductReviewAlreadyExistingException;
import com.auctix.auctx.exception.ProductReviewNotFoundException;
import com.auctix.auctx.model.ProductReview;
import com.auctix.auctx.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductReviewService implements IProductReviewService {
    private final ProductReviewRepository productReviewRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductReviewService.class);

    @Override
    public ProductReview save(ProductReview productReview) {
        logger.info("save: " + productReview.getId());
        return productReviewRepository.save(productReview);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("deleteById: " + id);
        productReviewRepository.findById(id).orElseThrow(ProductReviewNotFoundException::new);
        productReviewRepository.deleteById(id);
    }

    @Override
    public ProductReview findById(Long id) {
        logger.info("findById: " + id);
        return productReviewRepository.findById(id).orElseThrow(ProductReviewNotFoundException::new);
    }

    @Override
    public ProductReview update(ProductReview productReview) {
        logger.info("update: " + productReview.getId());
        productReviewRepository.findById(productReview.getId()).orElseThrow(ProductReviewNotFoundException::new);
        return productReviewRepository.save(productReview);
    }

    @Override
    public List<ProductReview> findByRating(Integer rating) {
        logger.info("findByRating: " + rating);
        return productReviewRepository.findAllByRating(rating);
    }

    @Override
    public List<ProductReview> findByRatingDesc() {
        logger.info("findByRatingDesc");
        return productReviewRepository.findAllByOrderByRatingDesc();
    }

    @Override
    public List<ProductReview> findByRatingAsc() {
        logger.info("findByRatingAsc");
        return productReviewRepository.findAllByOrderByRatingAsc();
    }

    @Override
    public List<ProductReview> findByDateAfter(LocalDateTime afterDate) {
        logger.info("findByDateAfter: " + afterDate);
        return productReviewRepository.findAllByPostDateAfter(afterDate);
    }

    @Override
    public List<ProductReview> findByDateBefore(LocalDateTime beforeDate) {
        logger.info("findByDateBefore: " + beforeDate);
        return productReviewRepository.findAllByPostDateBefore(beforeDate);
    }

    @Override
    public List<ProductReview> findByDateInterval(LocalDateTime afterDate, LocalDateTime beforeDate) {
        logger.info("findByDateInterval: " + afterDate + " " + beforeDate);
        return productReviewRepository.findAllByPostDateAfterAndPostDateBefore(afterDate, beforeDate);
    }

    @Override
    public List<ProductReview> findAllProductReviewsByProductId(Long productId) {
        logger.info("findAllProductReviewsByProductId: " + productId);
        return productReviewRepository.findAllByProductIdOrderByIdDesc(productId);
    }

    @Override
    public Integer findReviewCountByProductId(Long productId) {
        logger.info("findReviewCountByProductId: " + productId);
        return productReviewRepository.countAllByProductId(productId);
    }
}
