package com.hydroyura.TechDocsManager.Data.Converters.Route;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationTypeEntity;

@Component(value = "OperationTypeConverter")
public class OperationTypeConverter implements IConverter<OperationTypeEntity, OperationTypeDTO> {

	
	@Override
	public OperationTypeEntity convertFromDtoToEntity(OperationTypeDTO dto) {
		
		OperationTypeEntity entity = new OperationTypeEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public OperationTypeDTO convertFromEntityToDto(OperationTypeEntity entity) {
		
		OperationTypeDTO dto = new OperationTypeDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		
		return dto;
	}

}
