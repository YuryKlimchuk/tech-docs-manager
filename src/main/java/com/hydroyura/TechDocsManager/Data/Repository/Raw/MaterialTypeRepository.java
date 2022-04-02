package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;

@Repository(value = "MaterialTypeRepository")
public interface MaterialTypeRepository extends JpaRepository<MaterialTypeEntity, Long> {}
