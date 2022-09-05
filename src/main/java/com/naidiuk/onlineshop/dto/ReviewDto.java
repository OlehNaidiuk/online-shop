package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private Long reviewId;
    private String value;
}
