package com.auctix.auctx.controller;

import com.auctix.auctx.converter.UserReviewConverter;
import com.auctix.auctx.dto.UserReviewDto;
import com.auctix.auctx.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserReviewController {
    private final UserReviewService userReviewService;

    private final UserReviewConverter userReviewConverter;

    @PostMapping(path = "/addUserReview")
    public UserReviewDto addUserReview(@RequestBody UserReviewDto userReviewDto) {
        return this.userReviewConverter.convertModelToDto(this.userReviewService.save(this.userReviewConverter.convertDtoToModel(userReviewDto)));
    }

    @PutMapping(path = "/updateUserReview")
    public UserReviewDto updateUserReview(@RequestBody UserReviewDto userReviewDto) {
        return this.userReviewConverter.convertModelToDto(this.userReviewService.update(this.userReviewConverter.convertDtoToModel(userReviewDto)));
    }

    @DeleteMapping(path = "/deleteUserReview/{id}")
    public void deleteUserReview(@PathVariable(name = "id") Long id) {
        this.userReviewService.deleteById(id);
    }

    @GetMapping(path = "/findUserReviewById/{id}")
    public UserReviewDto findUserReviewById(@PathVariable(name = "id") Long id) {
        return this.userReviewConverter.convertModelToDto(this.userReviewService.findById(id));
    }

    @GetMapping(path = "/findUserReviewsByRating/{rating}")
    public List<UserReviewDto> findUserReviewsByRating(@PathVariable(name = "rating") Integer rating) {
        return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findByRating(rating));
    }

    @GetMapping(path = "/findUserReviewsOrderByRating/{descending}")
    public List<UserReviewDto> findUserReviewsDto(@PathVariable(name = "descending", required = false) Boolean descending) {
        if (descending == null || !descending) {
            return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findAllOrderByRatingAsc());
        }
        return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findAllOrderByRatingDesc());
    }

    @GetMapping(path = "/findUserReviewsAfterDate")
    public List<UserReviewDto> findUserReviewsAfterDate(
            @RequestParam(name = "afterDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterDate
    ) {
        return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findByDateAfter(
                afterDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        ));
    }

    @GetMapping(path = "/findUserReviewsBeforeDate")
    public List<UserReviewDto> findUserReviewsBeforeDate(
            @RequestParam(name = "beforeDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeDate
    ) {
        return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findByDateBefore(
                beforeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        ));
    }

    @GetMapping(path = "/findUserReviewsInDateInterval")
    public List<UserReviewDto> findUserReviewsInDateInterval(
            @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        return this.userReviewConverter.convertModelListToDtoList(this.userReviewService.findByDateInterval(
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        ));
    }
}
