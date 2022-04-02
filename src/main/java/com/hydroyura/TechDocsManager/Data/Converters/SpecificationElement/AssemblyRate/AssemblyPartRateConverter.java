package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

@Component(value = "AssemblyPartRateConverter")
public class AssemblyPartRateConverter extends AbstractAssemblyRateConverter<PartEntity, PartDTO> {
	
	@Autowired
	public AssemblyPartRateConverter(@Qualifier(value = "PartConverter") IConverter<PartEntity, PartDTO> itemConverter,
									 @Qualifier(value = "AssemblyPartRateFactory") IAssemblyRateFactory<PartEntity> factory) {
		
		this.itemConverter = itemConverter;
		this.factory = factory;
	}
}

