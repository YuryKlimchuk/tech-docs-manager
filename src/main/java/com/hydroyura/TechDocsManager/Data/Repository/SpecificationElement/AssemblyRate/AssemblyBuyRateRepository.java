package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;

@Repository(value = "AssemblyBuyRateRepository")
public interface AssemblyBuyRateRepository extends BaseAssemblyRateRepository<BuyEntity> {
	
	@Query(value = "SELECT b FROM AssemblyBuyRateEntity b WHERE b.assembly = :assembly")
	public List<AbstractAssemblyRateEntity<BuyEntity>> findAsseblyRateByAssembly(@Param(value = "assembly") AssemblyEntity assembly);
	
}
