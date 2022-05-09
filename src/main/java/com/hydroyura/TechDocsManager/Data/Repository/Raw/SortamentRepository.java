package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "SortamentRepository")
public interface SortamentRepository extends BaseRepositoryWithSpecification<SortamentEntity> {}
