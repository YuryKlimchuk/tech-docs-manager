package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.EquipmentEntity;

@Repository(value = "EquipmentRepository")
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {}
