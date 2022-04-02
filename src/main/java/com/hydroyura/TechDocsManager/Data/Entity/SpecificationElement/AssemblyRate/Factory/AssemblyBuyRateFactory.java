package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyBuyRateEntity;

@Component(value = "AssemblyBuyRateFactory")
public class AssemblyBuyRateFactory implements IAssemblyRateFactory<BuyEntity> {

	@Override
	public AbstractAssemblyRateEntity<BuyEntity> getInstance() {
		return new AssemblyBuyRateEntity();
	}

}
