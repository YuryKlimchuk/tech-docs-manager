package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;

@Repository(value = "BuyRepository")
public interface BuyRepository extends BaseRepositoryWithSpecification<BuyEntity> {}
