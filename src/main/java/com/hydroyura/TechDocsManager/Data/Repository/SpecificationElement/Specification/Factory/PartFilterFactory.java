package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.AbstractSearchFilter;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.PartSpecification;

@Component(value = "PartFilterFactory")
public class PartFilterFactory implements IFilterFactory<PartEntity> {

	@Override
	public AbstractSearchFilter<PartEntity> create(Map<String, String> params) {
		return new PartSpecification(params);
	}

}
