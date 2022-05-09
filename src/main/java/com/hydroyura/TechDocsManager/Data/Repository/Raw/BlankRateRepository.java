package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "BlankRateRepository")
public interface BlankRateRepository extends BaseRepositoryWithSpecification<BlankRateEntity> {

}
