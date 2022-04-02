package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyBuyRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

@Service(value = "AssemblyBuyRateService")
public class AssemblyBuyRateService extends AbstractAssemblyRateService<BuyEntity, BuyDTO> {
	
	
	@Autowired
	public AssemblyBuyRateService(
			@Qualifier(value = "AssemblyBuyRateConverter") AbstractAssemblyRateConverter<BuyEntity, BuyDTO> converter,
			@Qualifier(value = "AssemblyBuyRateRepository") BaseAssemblyRateRepository<BuyEntity> repository,
			@Qualifier(value = "AssemblyBuyRateBaseRepository") JpaRepository<AssemblyBuyRateEntity, Long> baseRepository) {
		
		
		this.converter = converter;
		this.repository = repository;
		this.baseRepository = baseRepository;
	}

}
