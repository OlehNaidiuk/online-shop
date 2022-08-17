package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Size;

public class SizeMapper {

    public static SizeDto transformToDto(Size size) {
        return SizeDto.builder()
                .sizeId(size.getSizeId())
                .value(size.getValue())
                .build();
    }
}
