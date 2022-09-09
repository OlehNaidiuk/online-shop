package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.ProductAllDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.ProductFilterDto;
import com.naidiuk.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
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
