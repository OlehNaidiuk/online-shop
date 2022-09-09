package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private Long reviewId;
    private String value;
}
