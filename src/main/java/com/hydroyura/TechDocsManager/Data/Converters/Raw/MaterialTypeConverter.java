package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;

@Component(value = "MaterialTypeConverter")
public class MaterialTypeConverter implements IConverter<MaterialTypeEntity, MaterialTypeDTO> {

	@Override
	public MaterialTypeEntity convertFromDtoToEntity(MaterialTypeDTO dto) {
		
		MaterialTypeEntity entity = new MaterialTypeEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public MaterialTypeDTO convertFromEntityToDto(MaterialTypeEntity entity) {
		
		MaterialTypeDTO dto = new MaterialTypeDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		
		return dto;
	}

}
