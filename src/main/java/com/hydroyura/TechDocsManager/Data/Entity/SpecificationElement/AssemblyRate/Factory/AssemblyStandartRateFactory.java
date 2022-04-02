package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyStandartRateEntity;

@Component(value = "AssemblyStandartRateFactory")
public class AssemblyStandartRateFactory implements IAssemblyRateFactory<StandartEntity> {

	@Override
	public AbstractAssemblyRateEntity<StandartEntity> getInstance() {
		return new AssemblyStandartRateEntity();
	}

}
