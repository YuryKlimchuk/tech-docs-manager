package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Repository(value = "PartRepositoryWithSpecification")
public interface PartRepositoryWithSpecification extends BaseRepositoryWithSpecification<PartEntity> {}
