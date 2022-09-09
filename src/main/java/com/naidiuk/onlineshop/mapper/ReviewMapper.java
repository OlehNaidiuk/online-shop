package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.ReviewDto;
import com.naidiuk.onlineshop.entity.Review;

public class ReviewMapper {

    public static ReviewDto transformToDto(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .value(review.getValue())
                .build();
    }

    public static Review transformToDao(ReviewDto reviewDto) {
        return Review.builder()
                .reviewId(reviewDto.getReviewId())
                .value(reviewDto.getValue())
                .build();
    }
}
