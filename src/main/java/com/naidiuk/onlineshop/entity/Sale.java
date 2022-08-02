package com.naidiuk.onlineshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;
    @Column(name = "sale_value")
    private BigDecimal value;
    @OneToMany(mappedBy = "sale")
    private List<Product> products;
}
