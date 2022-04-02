package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

@Component(value = "AssemblyVzkRateConverter")
public class AssemblyVzkRateConverter extends AbstractAssemblyRateConverter<VzkEntity, VzkDTO> {
	
	@Autowired
	public AssemblyVzkRateConverter(@Qualifier(value = "VzkConverter") IConverter<VzkEntity, VzkDTO> itemConverter,
									 @Qualifier(value = "AssemblyVzkRateFactory") IAssemblyRateFactory<VzkEntity> factory) {
		
		this.itemConverter = itemConverter;
		this.factory = factory;
	}

}

