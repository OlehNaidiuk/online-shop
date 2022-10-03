package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findAllBy(HttpServletRequest request, ProductFilterDto productFilter) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        List<ProductCategorySizesDto> productsDto = productService.findAllBy(productFilter);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @PostMapping("/{productId}/reviews")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> addReview(HttpServletRequest request,
                                       @PathVariable Long productId,
                                       @RequestBody ReviewDto reviewDto) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        ProductReviewsDto productReviewsDto = productService.saveProductReview(productId, reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body(productReviewsDto);
    }

    @DeleteMapping("/{productId}/reviews/{reviewId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> removeReview(HttpServletRequest request,
                                          @PathVariable Long productId,
                                          @PathVariable Long reviewId) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        ProductReviewsDto productReviewsDto = productService.deleteProductReview(productId, reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(productReviewsDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody ProductAllDto productAllDto) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        ProductAllDto savedProductDto = productService.save(productAllDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedProductDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody ProductDto productDtoToUpdate) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        ProductDto updatedProductDto = productService.update(productDtoToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProductDto);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Long productId) {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        ProductDto deletedProductDto = productService.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedProductDto);
    }
}
