package com.miniproject.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.backend.model.Categories;
import com.miniproject.backend.model.Products;
import com.miniproject.backend.repository.CategoriesRepository;
import com.miniproject.backend.repository.ProductsRepository;
import com.miniproject.backend.service.ProductsService;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(Integer id) {
        Optional<Products> productOptional = productsRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    public void addProduct(Products product) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(product.getCategories().getId());
        if (categoryOptional.isPresent()) {
            Categories category = categoryOptional.get();
            product.setCategories(category);
            productsRepository.save(product);
        } else {
            throw new RuntimeException("Category with ID " + product.getCategories().getId() + " not found");
        }
    }

    @Override
    public void updateProduct(Integer id, Products product) {
        Optional<Products> existingProductOptional = productsRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Products existingProduct = existingProductOptional.get();
            existingProduct.setTitle(product.getTitle());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());

            Optional<Categories> categoryOptional = categoriesRepository.findById(product.getCategories().getId());
            if (categoryOptional.isPresent()) {
                Categories category = categoryOptional.get();
                existingProduct.setCategories(category);
                productsRepository.save(existingProduct);
            } else {
                throw new RuntimeException("Category with ID " + product.getCategories().getId() + " not found");
            }
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    public void deleteProductById(Integer id) {
        productsRepository.deleteById(id);
    }

    @Override
    public List<Products> getProductsByCategory(Categories categories) {
        return productsRepository.findByCategories(categories);
    }

    @Override
    public List<Products> searchProductsByTitle(String title) {
        return productsRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Products> searchProductsByPrice(Integer price) {
        return productsRepository.findByPriceLessThanEqualOrderByPriceAsc(price);
    }
    
    
}
