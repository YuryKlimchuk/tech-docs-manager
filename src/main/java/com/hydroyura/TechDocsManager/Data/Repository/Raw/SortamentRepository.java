package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;

@Repository(value = "SortamentRepository")
public interface SortamentRepository extends JpaRepository<SortamentEntity, Long> {}
