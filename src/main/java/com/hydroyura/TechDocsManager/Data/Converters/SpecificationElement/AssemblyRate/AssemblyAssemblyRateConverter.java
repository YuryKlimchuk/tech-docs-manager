package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

@Component(value = "AssemblyAssemblyRateConverter")
public class AssemblyAssemblyRateConverter extends AbstractAssemblyRateConverter<AssemblyEntity, AssemblyDTO> {
	
	@Autowired
	public AssemblyAssemblyRateConverter(@Qualifier(value = "AssemblyConverter") IConverter<AssemblyEntity, AssemblyDTO> itemConverter,
									 @Qualifier(value = "AssemblyAssemblyRateFactory") IAssemblyRateFactory<AssemblyEntity> factory) {
		
		this.itemConverter = itemConverter;
		this.factory = factory;
	}

}

