package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;

@Component(value = "MaterialConverter")
public class MaterialConverter implements IConverter<MaterialEntity, MaterialDTO> {
	
	@Autowired
	@Qualifier(value = "MaterialTypeConverter")
	private IConverter<MaterialTypeEntity, MaterialTypeDTO> materialTypeConverter;

	@Override
	public MaterialEntity convertFromDtoToEntity(MaterialDTO dto) {
		
		MaterialEntity entity = new MaterialEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setName(dto.getStandart());
		entity.setType(materialTypeConverter.convertFromDtoToEntity(dto.getType()));
		
		return entity;
	}

	@Override
	public MaterialDTO convertFromEntityToDto(MaterialEntity entity) {
		
		MaterialDTO dto = new MaterialDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setStandart(entity.getName());
		dto.setType(materialTypeConverter.convertFromEntityToDto(entity.getType()));
		
		return dto;
	}

}
