package com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

public class AssemblySpecification extends AbstractSearchFilter<AssemblyEntity> {

	private String nameLike = null;
	private String numberLike = null;
	
	public AssemblySpecification(Map<String, String> params) {
		super(params);
		
		this.nameLike = (params.containsKey("name")) ? ("%" + params.get("name") + "%"): "%%";
		this.numberLike = (params.containsKey("number")) ? ("%" + params.get("number") + "%"): "%%";
		
		System.out.println("nameLike = " + nameLike);
		System.out.println("numberLike = " + numberLike);
	}

	
	
	private Specification<AssemblyEntity> getNumberSpecification() {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.<String>get("number"), numberLike);
		};
	}
	
	private Specification<AssemblyEntity> getNameSpecification() {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.<String>get("name"), nameLike);
		};
	}
	
	@Override
	public Specification<AssemblyEntity> getSpecification() {
		return getNumberSpecification().and(getNameSpecification());
	}

}

 