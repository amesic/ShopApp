package com.ajla.shop.repository;

import com.ajla.shop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findBrandById(Long id);
    Brand findBrandByName(String name);
}
