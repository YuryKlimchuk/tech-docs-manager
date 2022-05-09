package com.hydroyura.TechDocsManager.Data.Repository.Route;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "OperationRepository2")
public interface OperationRepository2 extends BaseRepositoryWithSpecification<OperationEntity> {
	
	List<OperationEntity> findByRoute_Id(long id);
	
}
