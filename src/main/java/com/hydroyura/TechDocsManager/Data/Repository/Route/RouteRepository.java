package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

@Repository(value = "RouteRepository")
public interface RouteRepository extends BaseRepositoryWithSpecification<RouteEntity> {
	
	public Iterable<RouteEntity> findByPart(PartEntity part);
	
}
