package com.hydroyura.TechDocsManager.Data.Repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;

@Repository(value = "CustomerRepository")
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {}
