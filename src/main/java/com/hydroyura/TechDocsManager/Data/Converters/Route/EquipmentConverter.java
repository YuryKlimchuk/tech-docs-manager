package com.hydroyura.TechDocsManager.Data.Converters.Route;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.EquipmentEntity;

@Component(value = "EquipmentConverter")
public class EquipmentConverter implements IConverter<EquipmentEntity, EquipmentDTO> {

	@Override
	public EquipmentEntity convertFromDtoToEntity(EquipmentDTO dto) {
		
		EquipmentEntity entity = new EquipmentEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public EquipmentDTO convertFromEntityToDto(EquipmentEntity entity) {
		
		EquipmentDTO dto = new EquipmentDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		
		return dto;
	}

}
