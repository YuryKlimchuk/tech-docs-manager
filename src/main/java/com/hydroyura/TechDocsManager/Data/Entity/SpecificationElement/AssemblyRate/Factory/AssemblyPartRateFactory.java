package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyPartRateEntity;

@Component(value = "AssemblyPartRateFactory")
public class AssemblyPartRateFactory implements IAssemblyRateFactory<PartEntity> {

	@Override
	public AbstractAssemblyRateEntity<PartEntity> getInstance() {
		return new AssemblyPartRateEntity();
	}

}
