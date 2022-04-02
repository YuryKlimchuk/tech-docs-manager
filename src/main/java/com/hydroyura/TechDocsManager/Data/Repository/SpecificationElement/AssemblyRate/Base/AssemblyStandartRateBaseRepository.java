package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.Base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyStandartRateEntity;

@Repository(value = "AssemblyStandartRateBaseRepository")
public interface AssemblyStandartRateBaseRepository extends JpaRepository<AssemblyStandartRateEntity, Long> {}
