package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;

@Repository(value = "AssemblyVzkRateRepository")
public interface AssemblyVzkRateRepository extends BaseAssemblyRateRepository<VzkEntity> {
	
	@Query(value = "SELECT b FROM AssemblyVzkRateEntity b WHERE b.assembly = :assembly")
	public List<AbstractAssemblyRateEntity<VzkEntity>> findAsseblyRateByAssembly(@Param(value = "assembly") AssemblyEntity assembly);
	
}
