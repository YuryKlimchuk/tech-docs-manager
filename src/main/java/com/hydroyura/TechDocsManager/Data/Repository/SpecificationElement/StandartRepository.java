package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;

@Repository(value = "StandartRepository")
public interface StandartRepository extends BaseRepositoryWithSpecification<StandartEntity> {}
