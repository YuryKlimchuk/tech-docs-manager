package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;

public class MaterialSpecification extends AbstractSearchFilter<MaterialEntity> {

	private String standartLike = null;
	private String numberLike = null;
	
	public MaterialSpecification(Map<String, String> params) {
		super(params);
		
		this.standartLike = (params.containsKey("standart")) ? ("%" + params.get("standart") + "%"): "%%";
		this.numberLike = (params.containsKey("number")) ? ("%" + params.get("number") + "%"): "%%";
		
		System.out.println("nameLike = " + standartLike);
		System.out.println("numberLike = " + numberLike);
	}

	
	
	private Specification<MaterialEntity> getNumberSpecification() {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.<String>get("number"), numberLike);
		};
	}
	
	private Specification<MaterialEntity> getStandartSpecification() {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.<String>get("name"), standartLike);
		};
	}
	
	@Override
	public Specification<MaterialEntity> getSpecification() {
		return getNumberSpecification().and(getStandartSpecification());
	}

}

 