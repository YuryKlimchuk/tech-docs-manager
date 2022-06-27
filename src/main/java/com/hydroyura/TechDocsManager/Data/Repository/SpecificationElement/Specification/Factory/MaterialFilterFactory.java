package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.AbstractSearchFilter;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.MaterialSpecification;

@Component(value = "MaterialFilterFactory")
public class MaterialFilterFactory implements IFilterFactory<MaterialEntity> {

	@Override
	public AbstractSearchFilter<MaterialEntity> create(Map<String, String> params) {
		return new MaterialSpecification(params);
	}

}
