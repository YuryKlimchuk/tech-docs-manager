package com.hydroyura.TechDocsManager.Data.Repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;

@Repository(value = "ProductRepository")
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
