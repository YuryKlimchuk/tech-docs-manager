package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyAssemblyRateEntity;

@Component(value = "AssemblyAssemblyRateFactory")
public class AssemblyAssemblyRateFactory implements IAssemblyRateFactory<AssemblyEntity> {

	@Override
	public AbstractAssemblyRateEntity<AssemblyEntity> getInstance() {
		return new AssemblyAssemblyRateEntity();
	}

}
