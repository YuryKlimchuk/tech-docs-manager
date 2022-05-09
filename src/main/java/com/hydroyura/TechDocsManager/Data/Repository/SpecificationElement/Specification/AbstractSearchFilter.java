package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSearchFilter<T> {
	
	protected Map<String, String> params;
	
	public AbstractSearchFilter(Map<String, String> params) {
		this.params = params;
	}
	
	public abstract Specification<T> getSpecification();
	
}