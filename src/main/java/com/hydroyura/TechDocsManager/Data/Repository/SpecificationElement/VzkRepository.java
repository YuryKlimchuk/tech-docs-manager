package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;

@Repository(value = "VzkRepository")
public interface VzkRepository extends JpaRepository<VzkEntity, Long> {}
