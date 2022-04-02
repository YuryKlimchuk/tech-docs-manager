package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;

@Repository(value = "StandartRepository")
public interface StandartRepository extends JpaRepository<StandartEntity, Long> {}
