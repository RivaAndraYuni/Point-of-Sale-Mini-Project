package com.miniproject.backend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miniproject.backend.model.Categories;
import com.miniproject.backend.repository.CategoriesRepository;
import com.miniproject.backend.service.CategoriesService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategoryById(Integer id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    @Override
    public void addCategory(Categories category) {
        categoriesRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, Categories category) {
        Categories existingCategory = categoriesRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            categoriesRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

}
