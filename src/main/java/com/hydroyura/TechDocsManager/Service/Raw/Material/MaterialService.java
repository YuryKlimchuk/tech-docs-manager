package com.hydroyura.TechDocsManager.Service.Raw.Material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Service(value = "MaterialService")
public class MaterialService extends AbstractSpecificationElementService<MaterialDTO, MaterialEntity, Long> {
	
	
	@Autowired
	public MaterialService(			
			@Qualifier(value = "MaterialRepository") JpaRepository<MaterialEntity, Long> repository,
			@Qualifier(value = "MaterialConverter") IConverter<MaterialEntity, MaterialDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
