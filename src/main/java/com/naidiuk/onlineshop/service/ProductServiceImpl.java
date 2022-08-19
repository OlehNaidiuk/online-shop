package com.naidiuk.onlineshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.naidiuk.onlineshop.dto.NBUCurrencyDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.error.JSONDeserializationException;
import com.naidiuk.onlineshop.error.RequestedCurrencyNotFoundException;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findTenRandom() {
        List<Product> tenRandomProducts = productRepository.findTenRandom();
        return tenRandomProducts.stream()
                                .map(ProductMapper::transformToDto)
                                .collect(Collectors.toList());
    }

    public ProductDto convertPriceByCurrency(String requestedCurrencyCode, ProductDto productDto) {
        String responseFromNBU = getResponseFromNBU();
        List<NBUCurrencyDto> nbuCurrencies = deserializeNBUCurrencies(responseFromNBU);
        NBUCurrencyDto requestedCurrency = getRequestedCurrency(nbuCurrencies, requestedCurrencyCode);
        if (requestedCurrency == null) {
            String message = "Requested currency not found.";
            throw new RequestedCurrencyNotFoundException(message);
        }
        BigDecimal productPriceByCurrency = convertProductPrice(productDto, requestedCurrency);

        return ProductDto.builder()
                .productId(productDto.getProductId())
                .price(productPriceByCurrency)
                .currency(Currency.getInstance(requestedCurrencyCode))
                .color(productDto.getColor())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .male(productDto.getMale())
                .sale(productDto.getSale())
                .build();
    }

    private String getResponseFromNBU() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL, String.class);
    }

    private List<NBUCurrencyDto> deserializeNBUCurrencies(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return List.of(objectMapper.readValue(response, NBUCurrencyDto[].class));
        } catch (JsonProcessingException e) {
            String message = "Can't deserialize NBU currencies.";
            throw new JSONDeserializationException(message);
        }
    }

    private NBUCurrencyDto getRequestedCurrency(List<NBUCurrencyDto> nbuCurrencies, String requestedCurrencyCode) {
        for (NBUCurrencyDto nbuCurrencyDto : nbuCurrencies) {
            String nbuCurrencyCode = nbuCurrencyDto.getCc();
            if (nbuCurrencyCode.equals(requestedCurrencyCode)) {
                return nbuCurrencyDto;
            }
        }
        return null;
    }

    private BigDecimal convertProductPrice(ProductDto productDto, NBUCurrencyDto requestedCurrency) {
        BigDecimal productPrice = productDto.getPrice();
        BigDecimal currencyRate = requestedCurrency.getRate();
        return productPrice.divide(currencyRate, 2, RoundingMode.HALF_UP);
    }
}
