package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

@Component(value = "AssemblyBuyRateConverter")
public class AssemblyBuyRateConverter extends AbstractAssemblyRateConverter<BuyEntity, BuyDTO> {
	
	@Autowired
	public AssemblyBuyRateConverter(@Qualifier(value = "BuyConverter") IConverter<BuyEntity, BuyDTO> itemConverter,
									 @Qualifier(value = "AssemblyBuyRateFactory") IAssemblyRateFactory<BuyEntity> factory) {
		
		this.itemConverter = itemConverter;
		this.factory = factory;
	}

}

