package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "MaterialTypeRepository")
public interface MaterialTypeRepository extends BaseRepositoryWithSpecification<MaterialTypeEntity> {}
