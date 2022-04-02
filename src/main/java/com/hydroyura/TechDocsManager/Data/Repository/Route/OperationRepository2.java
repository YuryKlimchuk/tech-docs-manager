package com.hydroyura.TechDocsManager.Data.Repository.Route;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;

@Repository(value = "OperationRepository2")
public interface OperationRepository2 extends JpaRepository<OperationEntity, Long> {
	
	List<OperationEntity> findByRoute_Id(long id);
	
}
