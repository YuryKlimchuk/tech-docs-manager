package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyStandartRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

@Service(value = "AssemblyStandartRateService")
public class AssemblyStandartRateService extends AbstractAssemblyRateService<StandartEntity, StandartDTO> {
	
	
	@Autowired
	public AssemblyStandartRateService(
			@Qualifier(value = "AssemblyStandartRateConverter") AbstractAssemblyRateConverter<StandartEntity, StandartDTO> converter,
			@Qualifier(value = "AssemblyStandartRateRepository") BaseAssemblyRateRepository<StandartEntity> repository,
			@Qualifier(value = "AssemblyStandartRateBaseRepository") JpaRepository<AssemblyStandartRateEntity, Long> baseRepository) {
		
		
		
		
		this.converter = converter;
		this.repository = repository;
		this.baseRepository = baseRepository;
	}

}
