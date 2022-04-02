package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;

@Repository(value = "BlankRepository")
public interface BlankRepository extends JpaRepository<BlankEntity, Long> {}
