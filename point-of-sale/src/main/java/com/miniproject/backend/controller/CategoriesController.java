package com.miniproject.backend.controller;

import com.miniproject.backend.dto.CategoriesDTO;
import com.miniproject.backend.model.Categories;
import com.miniproject.backend.response.ResponseModel;
import com.miniproject.backend.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pos/api")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/listcategory")
    public List<CategoriesDTO> listCategories() {
        List<Categories> categories = categoriesService.getAllCategories();
        List<CategoriesDTO> response = categories.stream()
                .map(category -> new CategoriesDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
        
        return response;
    }
    
    @GetMapping("/category/{id}")
    public CategoriesDTO getCategoryById(@PathVariable Integer id) {
        Categories category = categoriesService.getCategoryById(id);
        if (category == null) {
            return null;
        }
        return new CategoriesDTO(category.getId(), category.getName());
    }

    @PostMapping("/addcategory")
    public ResponseModel<CategoriesDTO> addCategory(@RequestBody CategoriesDTO request) {
        ResponseModel<CategoriesDTO> resp = new ResponseModel<>();
        Categories category = new Categories();
        category.setName(request.getName());
        categoriesService.addCategory(category);
        resp.setStatus("ok");
        resp.setMessage("success");
        return resp;
    }

    @PutMapping("/updatecategory/{id}")
    public ResponseModel<CategoriesDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoriesDTO request) {
        ResponseModel<CategoriesDTO> resp = new ResponseModel<>();
        Categories category = categoriesService.getCategoryById(id);
        if (category == null) {
            resp.setStatus("error");
            resp.setMessage("Category not found with id: " + id);
            return resp;
        }
        category.setName(request.getName());
        categoriesService.updateCategory(id, category);
        resp.setStatus("ok");
        resp.setMessage("success");
        return resp;
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseModel<CategoriesDTO> deleteCategory(@PathVariable Integer id) {
        ResponseModel<CategoriesDTO> resp = new ResponseModel<>();
        Categories category = categoriesService.getCategoryById(id);
        if (category == null) {
            resp.setStatus("error");
            resp.setMessage("Category not found with id: " + id);
            return resp;
        }
        categoriesService.deleteCategoryById(id);
        resp.setStatus("ok");
        resp.setMessage("success");
        return resp;
    }
}
