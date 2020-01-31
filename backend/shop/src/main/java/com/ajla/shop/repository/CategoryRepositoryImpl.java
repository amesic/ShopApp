package com.ajla.shop.repository;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final EntityManager em;

    public CategoryRepositoryImpl(final EntityManager em) {
        Objects.requireNonNull(em, "em must not be null.");
        this.em = em;
    }

    @Override
    public List<Category> getAllCategories() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        final Root<Category> category = cq.from(Category.class);

        cq.where(cb.isNotEmpty(category.get("subcategories")));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Category> getSubcategoriesOfCategory(final String category_name) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        final Root<Category> category = cq.from(Category.class);

        cq.select(category.get("subcategories"))
                .where(cb.equal(category.get("name"), category_name));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Brand> getCategoryBrands(final String category_name) {

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        final Root<Category> category = cq.from(Category.class);

        cq.where(cb.equal(category.get("name"), category_name));

        Category category_with_name = em.createQuery(cq).getSingleResult();

        final CriteriaQuery<Brand> cqBrand = cb.createQuery(Brand.class);
        final Root<Brand> brand = cqBrand.from(Brand.class);

        cqBrand.select(brand.get("brands")).where(cb.equal(brand.get("id"), category_with_name.getBrand().getId()));
        return em.createQuery(cqBrand).getResultList();
    }

}
