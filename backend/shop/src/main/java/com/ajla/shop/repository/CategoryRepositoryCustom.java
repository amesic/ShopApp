package com.ajla.shop.repository;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> getAllCategories();
    List<Category> getSubcategoriesOfCategory(final String category_name);
    List<Brand> getCategoryBrands(final String category_name);

}
