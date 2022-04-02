package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

@Service(value = "AssemblyAssemblyRateService")
public class AssemblyAssemblyRateService extends AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> {
	
	
	@Autowired
	public AssemblyAssemblyRateService(
			@Qualifier(value = "AssemblyAssemblyRateConverter") AbstractAssemblyRateConverter<AssemblyEntity, AssemblyDTO> converter,
			@Qualifier(value = "AssemblyAssemblyRateRepository") BaseAssemblyRateRepository<AssemblyEntity> repository,
			@Qualifier(value = "AssemblyAssemblyRateBaseRepository") JpaRepository<AssemblyAssemblyRateEntity, Long> baseRepository) {
		
		this.converter = converter;
		this.repository = repository;
		this.baseRepository = baseRepository;
	}

}
