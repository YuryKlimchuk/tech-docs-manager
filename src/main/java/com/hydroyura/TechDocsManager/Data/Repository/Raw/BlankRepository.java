package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "BlankRepository")
public interface BlankRepository extends BaseRepositoryWithSpecification<BlankEntity> {}
