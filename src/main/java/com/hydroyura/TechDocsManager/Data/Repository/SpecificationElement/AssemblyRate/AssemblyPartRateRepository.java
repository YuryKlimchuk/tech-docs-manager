package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;

@Repository(value = "AssemblyPartRateRepository")
public interface AssemblyPartRateRepository extends BaseAssemblyRateRepository<PartEntity> {
	
	@Query(value = "SELECT b FROM AssemblyPartRateEntity b WHERE b.assembly = :assembly")
	public List<AbstractAssemblyRateEntity<PartEntity>> findAsseblyRateByAssembly(@Param(value = "assembly") AssemblyEntity assembly);
	
}
