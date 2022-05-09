package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

@Repository(value = "AssemblyRepository")
public interface AssemblyRepository extends BaseRepositoryWithSpecification<AssemblyEntity> {}
