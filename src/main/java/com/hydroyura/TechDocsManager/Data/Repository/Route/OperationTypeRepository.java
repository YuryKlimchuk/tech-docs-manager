package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationTypeEntity;

@Repository(value = "OperationTypeRepository")
public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Long> {}
