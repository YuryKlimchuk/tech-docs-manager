package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.EquipmentEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "EquipmentRepository")
public interface EquipmentRepository extends BaseRepositoryWithSpecification<EquipmentEntity> {}
