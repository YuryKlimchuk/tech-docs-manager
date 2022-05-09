package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "MaterialRepository")
public interface MaterialRepository extends BaseRepositoryWithSpecification<MaterialEntity> {}
