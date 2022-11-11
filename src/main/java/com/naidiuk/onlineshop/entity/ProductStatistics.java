package com.naidiuk.onlineshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "product_statistics")
public class ProductStatistics {
    @Id
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_views")
    private long productViews;
    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}
