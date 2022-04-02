package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyVzkRateEntity;

@Component(value = "AssemblyVzkRateFactory")
public class AssemblyVzkRateFactory implements IAssemblyRateFactory<VzkEntity> {

	@Override
	public AbstractAssemblyRateEntity<VzkEntity> getInstance() {
		return new AssemblyVzkRateEntity();
	}

}
