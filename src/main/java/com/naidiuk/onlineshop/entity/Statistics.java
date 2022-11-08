package com.naidiuk.onlineshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "statistics")
public class Statistics {
    @Id
    @Column(name = "product_id")
    private Long id;
    private long views;
    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}
