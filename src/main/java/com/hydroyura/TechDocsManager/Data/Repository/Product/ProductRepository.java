package com.hydroyura.TechDocsManager.Data.Repository.Product;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "ProductRepository")
public interface ProductRepository extends BaseRepositoryWithSpecification<ProductEntity> {}
