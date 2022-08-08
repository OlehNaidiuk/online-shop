package com.naidiuk.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private Category category;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonManagedReference
    private Company company;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonManagedReference
    private Sale sale;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Review> reviews;
}
