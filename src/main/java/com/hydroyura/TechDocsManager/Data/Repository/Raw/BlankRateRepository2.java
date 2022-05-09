package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "BlankRateRepository2")
public interface BlankRateRepository2 extends BaseRepositoryWithSpecification<BlankRateEntity> {

	
	List<BlankRateEntity> findByRoute_Id(long id);

}
