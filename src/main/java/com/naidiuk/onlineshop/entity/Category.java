package com.naidiuk.onlineshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Catalog catalog;
    @ManyToMany(mappedBy = "categorySizes")
    private List<Size> sizes;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
