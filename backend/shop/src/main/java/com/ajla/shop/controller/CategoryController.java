package com.ajla.shop.controller;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;
import com.ajla.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200", "https://shopwisely-app.herokuapp.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService) {
        Objects.requireNonNull(categoryService, "categoryService must not be null.");
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/category/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/category/subcategories")
    public ResponseEntity<List<Category>> getSubcategoriesOfCategory(@RequestParam("name") final String name) {
        return new ResponseEntity<>(categoryService.getSubcategoriesOfCategory(name), HttpStatus.OK);
    }

    @GetMapping(value = "/category/brands")
    public ResponseEntity<List<Brand>> getBrandsOfCategory(@RequestParam("name") final String name) {
        return new ResponseEntity<>(categoryService.getCategoryBrands(name), HttpStatus.OK);
    }
}
