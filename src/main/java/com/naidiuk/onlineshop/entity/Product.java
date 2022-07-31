package com.naidiuk.onlineshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(name = "product_name")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Male male;
}
