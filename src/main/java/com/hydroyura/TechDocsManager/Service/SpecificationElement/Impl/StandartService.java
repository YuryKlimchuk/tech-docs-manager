package com.hydroyura.TechDocsManager.Service.SpecificationElement.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Component(value = "StandartService")
public class StandartService extends AbstractSpecificationElementService<StandartDTO, StandartEntity> {

	
	@Autowired
	public StandartService(
			@Qualifier(value = "StandartRepository") BaseRepositoryWithSpecification<StandartEntity> repository,
			@Qualifier(value = "StandartConverter") IConverter<StandartEntity, StandartDTO> converter) {
		
		this.repository = repository;
		this.converter = converter;
		
	}
	
}
