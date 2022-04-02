package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;

public interface IAssemblyRateFactory<T> {

	public AbstractAssemblyRateEntity<T> getInstance();
	
}
