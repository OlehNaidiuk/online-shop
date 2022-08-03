package com.naidiuk.onlineshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "catalog_type")
    private CatalogType catalogType;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_size",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private List<Size> sizes;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Builder
    public Category(Long categoryId, String name, CatalogType catalogType, List<Size> sizes, List<Product> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.catalogType = catalogType;
        this.sizes = sizes;
        this.products = products;
    }
}
