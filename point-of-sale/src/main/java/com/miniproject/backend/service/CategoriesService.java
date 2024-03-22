package com.miniproject.backend.service;

import java.util.List;

import com.miniproject.backend.model.Categories;

public interface CategoriesService {

	List<Categories> getAllCategories();
    Categories getCategoryById(Integer id);
    void addCategory(Categories category);
    void updateCategory(Integer id, Categories category);
    void deleteCategoryById(Integer id);
    
}
