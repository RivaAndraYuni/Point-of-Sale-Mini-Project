package com.miniproject.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.backend.model.Categories;
import com.miniproject.backend.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findByTitleContainingIgnoreCase(String title);
    List<Products> findByPriceLessThanEqualOrderByPriceAsc(Integer price);
    List<Products> findByCategories(Categories categories);
}
