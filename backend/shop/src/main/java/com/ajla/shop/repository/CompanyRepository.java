package com.ajla.shop.repository;

import com.ajla.shop.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyById(Long id);
    Company findCompanyByName(String name);
}
