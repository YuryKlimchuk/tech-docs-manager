package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;

@Repository(value = "SortamentTypeRepository")
public interface SortamentTypeRepository extends JpaRepository<SortamentTypeEntity, Long> {}
