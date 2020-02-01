package com.ajla.shop.repository;

import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final EntityManager em;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductRepositoryImpl(final EntityManager em,
                                 final CategoryRepository categoryRepository) {
        Objects.requireNonNull(em, "em must not be null.");
        Objects.requireNonNull(categoryRepository, "categoryRepository must not be null.");
        this.categoryRepository = categoryRepository;
        this.em = em;
    }

    @Override
    public PaginationInfo<Product> getAllNewArrivalProducts(final Long pageNumber, final Long size) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        final Root<Product> product = cq.from(Product.class);

        final CriteriaQuery<Long> cqForSizeOfProducts = cb.createQuery(Long.class);
        final Root<Product> productForSizeOfProducts = cqForSizeOfProducts.from(Product.class);

        cqForSizeOfProducts.select(cb.count(productForSizeOfProducts))
                .where(cb.lessThanOrEqualTo(productForSizeOfProducts.get("date_published"), LocalDateTime.now()));
        cq.where(cb.lessThanOrEqualTo(product.get("date_published"), LocalDateTime.now()))
                .orderBy(cb.desc(product.get("date_published")));

        final TypedQuery<Product> queryForListOfProducts = em.createQuery(cq);
        //pageNumber starts from 0, return list of size number elements, starting from pageNumber*size index of element
        queryForListOfProducts.setFirstResult(Math.toIntExact(pageNumber * size));
        queryForListOfProducts.setMaxResults(Math.toIntExact(size));
        final List<Product> listOfProducts = queryForListOfProducts.getResultList();

        //set total number of last chance products
        final TypedQuery<Long> queryForSizeListOfProducts = em.createQuery(cqForSizeOfProducts);
        final Long totalNumberOfItems = queryForSizeListOfProducts.getSingleResult();

        return new PaginationInfo<>(size, pageNumber, totalNumberOfItems, listOfProducts);
    }
}
