package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyVzkRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

@Service(value = "AssemblyVzkRateService")
public class AssemblyVzkRateService extends AbstractAssemblyRateService<VzkEntity, VzkDTO> {
	
	
	@Autowired
	public AssemblyVzkRateService(
			@Qualifier(value = "AssemblyVzkRateConverter") AbstractAssemblyRateConverter<VzkEntity, VzkDTO> converter,
			@Qualifier(value = "AssemblyVzkRateRepository") BaseAssemblyRateRepository<VzkEntity> repository,
			@Qualifier(value = "AssemblyVzkRateBaseRepository") JpaRepository<AssemblyVzkRateEntity, Long> baseRepository) {
		
		
		this.converter = converter;
		this.repository = repository;
		this.baseRepository = baseRepository;
	}

}
