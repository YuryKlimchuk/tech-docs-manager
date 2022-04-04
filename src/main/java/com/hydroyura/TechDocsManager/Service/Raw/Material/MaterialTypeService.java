package com.hydroyura.TechDocsManager.Service.Raw.Material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "MaterialTypeService")
public class MaterialTypeService extends AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity, Long> {
	
	
	@Autowired
	public MaterialTypeService(			
			@Qualifier(value = "MaterialTypeRepository") JpaRepository<MaterialTypeEntity, Long> repository,
			@Qualifier(value = "MaterialTypeConverter") IConverter<MaterialTypeEntity, MaterialTypeDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
