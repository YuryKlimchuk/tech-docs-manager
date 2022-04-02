package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;

@Repository(value = "OperationRepository")
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {}
