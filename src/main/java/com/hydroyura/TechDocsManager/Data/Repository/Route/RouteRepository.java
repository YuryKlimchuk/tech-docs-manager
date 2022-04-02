package com.hydroyura.TechDocsManager.Data.Repository.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Repository(value = "RouteRepository")
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
	
	public Iterable<RouteEntity> findByPart(PartEntity part);
	
}
