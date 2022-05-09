package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationTypeEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "OperationTypeRepository")
public interface OperationTypeRepository extends BaseRepositoryWithSpecification<OperationTypeEntity> {}
