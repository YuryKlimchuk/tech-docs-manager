package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Repository(value = "PartRepository")
public interface PartRepository extends JpaRepository<PartEntity, Long> {

}
