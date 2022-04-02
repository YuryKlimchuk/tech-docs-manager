package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;

@Component(value = "SortamentTypeConverter")
public class SortamentTypeConverter implements IConverter<SortamentTypeEntity, SortamentTypeDTO> {

	@Override
	public SortamentTypeEntity convertFromDtoToEntity(SortamentTypeDTO dto) {
		
		SortamentTypeEntity entity = new SortamentTypeEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public SortamentTypeDTO convertFromEntityToDto(SortamentTypeEntity entity) {
		
		SortamentTypeDTO dto = new SortamentTypeDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		
		return dto;
	}

}
