package com.ajla.shop.service;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;
import com.ajla.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository) {
        Objects.requireNonNull(categoryRepository, "categoryRepository must not be null.");
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getSubcategoriesOfCategory(final String name) {
        return categoryRepository.getSubcategoriesOfCategory(name);
    }

    @Override
    public List<Brand> getCategoryBrands(final String category_name) {
        return categoryRepository.getCategoryBrands(category_name);
    }

}
