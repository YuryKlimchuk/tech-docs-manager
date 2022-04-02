package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;

@Component(value = "SortamentConverter")
public class SortamentConverter implements IConverter<SortamentEntity, SortamentDTO> {
	
	@Autowired
	@Qualifier(value = "SortamentTypeConverter")
	private IConverter<SortamentTypeEntity, SortamentTypeDTO> sortamentTypeConverter;

	@Override
	public SortamentEntity convertFromDtoToEntity(SortamentDTO dto) {
		
		SortamentEntity entity = new SortamentEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setName(dto.getStandart());
		entity.setType(sortamentTypeConverter.convertFromDtoToEntity(dto.getType()));
		
		return entity;
	}

	@Override
	public SortamentDTO convertFromEntityToDto(SortamentEntity entity) {
		
		SortamentDTO dto = new SortamentDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setStandart(entity.getName());
		dto.setType(sortamentTypeConverter.convertFromEntityToDto(entity.getType()));
		
		return dto;
	}
	

}
