package com.hydroyura.TechDocsManager.Service.SpecificationElement.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "VzkService")
public class VzkService extends AbstractSpecificationElementService<VzkDTO, VzkEntity> {
	
	@Autowired
	public VzkService(			
			@Qualifier(value = "VzkRepository") BaseRepositoryWithSpecification<VzkEntity> repository,
			@Qualifier(value = "VzkConverter") IConverter<VzkEntity, VzkDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}

}
