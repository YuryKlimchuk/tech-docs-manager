package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "OperationRepository")
public interface OperationRepository extends BaseRepositoryWithSpecification<OperationEntity> {}
