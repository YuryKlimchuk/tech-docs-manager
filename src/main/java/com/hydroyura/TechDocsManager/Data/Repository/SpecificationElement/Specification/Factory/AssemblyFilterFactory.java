package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.AbstractSearchFilter;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.AssemblySpecification;

@Component(value = "AssemblyFilterFactory")
public class AssemblyFilterFactory implements IFilterFactory<AssemblyEntity> {

	@Override
	public AbstractSearchFilter<AssemblyEntity> create(Map<String, String> params) {
		return new AssemblySpecification(params);
	}

}
