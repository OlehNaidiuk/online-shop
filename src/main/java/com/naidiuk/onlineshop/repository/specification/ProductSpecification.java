package com.naidiuk.onlineshop.repository.specification;

import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> filterByCategories(List<Long> categoryIds) {
        return (root, query, criteriaBuilder) -> {
            root.fetch("category", JoinType.LEFT);
            if (categoryIds == null) {
                return null;
            }
            return root.join("category", JoinType.LEFT).in(categoryIds);
        };
    }

    public static Specification<Product> filterBySizes(List<Long> sizeIds) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            root.fetch("sizes", JoinType.LEFT);
            if (sizeIds == null) {
                return null;
            }
            return root.join("sizes", JoinType.LEFT).in(sizeIds);
        };
    }

    public static Specification<Product> filterByColors(List<Color> colors) {
        if (colors == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                root.get("color").in(colors);
    }

    public static Specification<Product> filterByPriceRange(Integer minPrice, Integer maxPrice) {
        if (minPrice == null) {
            minPrice = 0;
        }
        if (maxPrice == null) {
            maxPrice = 100_000;
        }
        Integer finalMinPrice = minPrice;
        Integer finalMaxPrice = maxPrice;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), finalMinPrice, finalMaxPrice);
    }

    public static Specification<Product> filterByQuery(String searchingQuery) {
        if (searchingQuery == null) {
            return null;
        }
        String pattern = "%" + searchingQuery.toLowerCase() + "%";
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        pattern
                );
    }
}
