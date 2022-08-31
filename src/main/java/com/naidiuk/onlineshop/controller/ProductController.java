package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/random")
    public ResponseEntity<List<ProductDto>> findTenRandom(HttpServletRequest request) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        List<ProductDto> productsDto = productService.findTenRandom();
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @GetMapping
    public ResponseEntity<?> findAllByFilters(
                    HttpServletRequest request,
                    @RequestParam(name = "category", required = false) List<Long> categoryIds,
                    @RequestParam(name = "size", required = false) List<Long> sizeIds,
                    @RequestParam(name = "color", required = false) List<Color> colors,
                    @RequestParam(defaultValue = "0") int minPrice,
                    @RequestParam(defaultValue = "100000") int maxPrice) {

        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        List<ProductCategorySizesDto> productsDto =
                productService.findAllByFilters(categoryIds, sizeIds, colors, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }
}
