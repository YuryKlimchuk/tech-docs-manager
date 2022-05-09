package com.hydroyura.TechDocsManager.Service.SpecificationElement.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "PartService")
public class PartService extends AbstractSpecificationElementService<PartDTO, PartEntity>{
	
	@Autowired
	public PartService(			
			@Qualifier(value = "PartRepository") BaseRepositoryWithSpecification<PartEntity> repository,
			@Qualifier(value = "PartConverter") IConverter<PartEntity, PartDTO> converter) {
		
		this.converter = converter;
		this.repository = repository;
	}

}
