package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;


@NoRepositoryBean
public interface BaseAssemblyRateRepository<T1> extends JpaRepository<AbstractAssemblyRateEntity<T1>, Long> {

	public List<AbstractAssemblyRateEntity<T1>> findAsseblyRateByAssembly(@Param(value = "assembly") AssemblyEntity assembly);
	
}





