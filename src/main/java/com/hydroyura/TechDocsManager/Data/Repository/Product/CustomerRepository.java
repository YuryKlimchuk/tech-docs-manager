package com.hydroyura.TechDocsManager.Data.Repository.Product;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "CustomerRepository")
public interface CustomerRepository extends BaseRepositoryWithSpecification<CustomerEntity> {}
