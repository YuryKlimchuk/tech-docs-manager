
package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyPartRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

@Service(value = "AssemblyPartRateService")
public class AssemblyPartRateService extends AbstractAssemblyRateService<PartEntity, PartDTO> {
	
	
	@Autowired
	public AssemblyPartRateService(
			@Qualifier(value = "AssemblyPartRateConverter") AbstractAssemblyRateConverter<PartEntity, PartDTO> converter,
			@Qualifier(value = "AssemblyPartRateRepository") BaseAssemblyRateRepository<PartEntity> repository,
			@Qualifier(value = "AssemblyPartRateBaseRepository") JpaRepository<AssemblyPartRateEntity, Long> baseRepository) {
		
		//AssemblyPartRateRepository
		
		
		this.converter = converter;
		this.repository = repository;
		this.baseRepository = baseRepository;
	}

}

