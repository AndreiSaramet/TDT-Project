package com.auctix.auctx.converter;

import com.auctix.auctx.dto.UserReviewDto;
import com.auctix.auctx.model.UserReview;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserReviewConverter implements IConverter<UserReview, UserReviewDto> {
    @Override
    public UserReview convertDtoToModel(UserReviewDto userReviewDto) {
        UserReview userReview = UserReview.builder()
                .id(userReviewDto.getId())
                .text(userReviewDto.getText())
                .rating(userReviewDto.getRating())
                .postDate(userReviewDto.getPostDate() == null ? LocalDateTime.now() : userReviewDto.getPostDate())
                .build();
        Users receiver = new Users();
        receiver.setId(userReviewDto.getReceiverId());
        userReview.setReceiver(receiver);
        Users poster = new Users();
        poster.setId(userReviewDto.getPosterId());
        userReview.setPoster(poster);
        return userReview;
    }

    @Override
    public UserReviewDto convertModelToDto(UserReview userReview) {
        if (userReview == null) {
            return new UserReviewDto();
        }
        return UserReviewDto.builder()
                .id(userReview.getId())
                .text(userReview.getText())
                .rating(userReview.getRating())
                .postDate(userReview.getPostDate())
                .receiverId(userReview.getReceiver().getId())
                .posterId(userReview.getPoster().getId())
                .build();
    }

    @Override
    public List<UserReviewDto> convertModelListToDtoList(List<UserReview> userReviews) {
        return userReviews.stream()
                .map(this::convertModelToDto)
                .toList();
    }
}
