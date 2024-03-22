package com.miniproject.backend.controller;

import com.miniproject.backend.dto.ProductsDTO;
import com.miniproject.backend.model.Categories;
import com.miniproject.backend.model.Products;
import com.miniproject.backend.response.ResponseModel;
import com.miniproject.backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.miniproject.backend.repository.CategoriesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pos/api")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CategoriesRepository categoriesRepository; 

    @GetMapping("/listproduct")
    public List<ProductsDTO> listProducts(@RequestParam(required = false) String title,
                                          @RequestParam(required = false) Integer price,
                                          @RequestParam(required = false) Integer categoryId, 
                                          @RequestParam(required = false) String sort_by,
                                          @RequestParam(required = false) String sort_order) {
        List<Products> products;

        if (categoryId != null) {
            Optional<Categories> optionalCategory = categoriesRepository.findById(categoryId);
            if (optionalCategory.isPresent()) {
                Categories category = optionalCategory.get();
                products = productsService.getProductsByCategory(category);
            } else {
                throw new RuntimeException("Category not found with ID: " + categoryId);
            }
        } else {
            products = productsService.getAllProducts();
        }


        if (title != null) {
            products = products.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        }


        if (price != null) {
            products = products.stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
        }

 
        if (sort_by != null && sort_order != null) {
            if (sort_by.equals("title")) {
                if (sort_order.equals("asc")) {
                    products.sort(Comparator.comparing(Products::getTitle));
                } else if (sort_order.equals("desc")) {
                    products.sort(Comparator.comparing(Products::getTitle).reversed());
                }
            } else if (sort_by.equals("price")) {
                if (sort_order.equals("asc")) {
                    products.sort(Comparator.comparing(Products::getPrice));
                } else if (sort_order.equals("desc")) {
                    products.sort(Comparator.comparing(Products::getPrice).reversed());
                }
            }
        }

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    
    @PostMapping("/addproduct")
    public ResponseModel<ProductsDTO> addProduct(@RequestBody ProductsDTO request) {
        ResponseModel<ProductsDTO> response = new ResponseModel<>();
        Products product = convertToEntity(request);
        productsService.addProduct(product);
        response.setStatus("ok");
        response.setMessage("success");
        return response;
    }

    @PutMapping("/updateproduct/{id}")
    public ResponseModel<ProductsDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductsDTO request) {
        ResponseModel<ProductsDTO> response = new ResponseModel<>();
        Products product = convertToEntity(request);
        productsService.updateProduct(id, product);
        response.setStatus("ok");
        response.setMessage("success");
        return response;
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseModel<ProductsDTO> deleteProduct(@PathVariable Integer id) {
        ResponseModel<ProductsDTO> response = new ResponseModel<>();
        productsService.deleteProductById(id);
        response.setStatus("ok");
        response.setMessage("success");
        return response;
    }

    @GetMapping("/detailproduct/{id}")
    public ProductsDTO detailProduct(@PathVariable Integer id) {
        Products product = productsService.getProductById(id);
        return convertToDTO(product);
    }

    private ProductsDTO convertToDTO(Products product) {
        ProductsDTO dto = new ProductsDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        dto.setCategories(product.getCategories().getId());
        return dto;
    }

    private Products convertToEntity(ProductsDTO dto) {
        Products product = new Products();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        Categories category = categoriesRepository.findById(dto.getCategories())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategories()));
        product.setCategories(category);
        return product;
    }

}
