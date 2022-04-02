package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.Base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyBuyRateEntity;

@Repository(value = "AssemblyBuyRateBaseRepository")
public interface AssemblyBuyRateBaseRepository extends JpaRepository<AssemblyBuyRateEntity, Long> {}
