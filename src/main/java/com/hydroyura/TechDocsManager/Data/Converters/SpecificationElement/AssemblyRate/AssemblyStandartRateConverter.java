package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

@Component(value = "AssemblyStandartRateConverter")
public class AssemblyStandartRateConverter extends AbstractAssemblyRateConverter<StandartEntity, StandartDTO> {
	
	@Autowired
	public AssemblyStandartRateConverter(@Qualifier(value = "StandartConverter") IConverter<StandartEntity, StandartDTO> itemConverter,
									 @Qualifier(value = "AssemblyStandartRateFactory") IAssemblyRateFactory<StandartEntity> factory) {
		
		this.itemConverter = itemConverter;
		this.factory = factory;
	}

}

