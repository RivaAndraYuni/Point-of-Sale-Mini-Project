package com.miniproject.backend.service;

import java.util.List;

import com.miniproject.backend.model.Categories;
import com.miniproject.backend.model.Products;

public interface ProductsService {
    
    List<Products> getAllProducts();
    List<Products> getProductsByCategory(Categories categories); 
    List<Products> searchProductsByTitle(String title);
    List<Products> searchProductsByPrice(Integer price);
    Products getProductById(Integer id);
    void addProduct(Products product);
    void updateProduct(Integer id, Products product);
    void deleteProductById(Integer id);

}
