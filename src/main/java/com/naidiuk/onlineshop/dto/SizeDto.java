package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SizeDto {
    private Long sizeId;
    private String value;
}
