package com.hydroyura.TechDocsManager.Service.Raw.Material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "MaterialService")
public class MaterialService extends AbstractSpecificationElementService<MaterialDTO, MaterialEntity> {
	
	
	@Autowired
	public MaterialService(			
			@Qualifier(value = "MaterialRepository") BaseRepositoryWithSpecification<MaterialEntity> repository,
			@Qualifier(value = "MaterialConverter") IConverter<MaterialEntity, MaterialDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
