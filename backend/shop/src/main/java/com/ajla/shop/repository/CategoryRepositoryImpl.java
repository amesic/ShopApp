package com.ajla.shop.repository;

import com.ajla.shop.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final EntityManager em;

    public CategoryRepositoryImpl(final EntityManager em) {
        Objects.requireNonNull(em, "em must not be null.");
        this.em = em;
    }



}
