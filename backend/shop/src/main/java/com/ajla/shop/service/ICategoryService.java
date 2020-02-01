package com.ajla.shop.service;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();
    List<Category> getSubcategoriesOfCategory(final String name);
    List<Brand> getCategoryBrands(final String category_name);
}
