package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory;

import java.util.Map;

import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.AbstractSearchFilter;

public interface IFilterFactory<T> {
	
	public AbstractSearchFilter<T> create(Map<String, String> params);

}
