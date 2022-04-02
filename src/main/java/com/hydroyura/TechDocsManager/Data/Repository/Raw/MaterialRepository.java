package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;

@Repository(value = "MaterialRepository")
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {}
