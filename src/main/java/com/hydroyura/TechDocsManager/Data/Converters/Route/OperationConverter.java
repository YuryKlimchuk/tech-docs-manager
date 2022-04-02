package com.hydroyura.TechDocsManager.Data.Converters.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.EquipmentEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationTypeEntity;

@Component(value = "OperationConverter")
public class OperationConverter implements IConverter<OperationEntity, OperationDTO> {

	@Autowired
	@Qualifier(value = "OperationTypeConverter")
	private IConverter<OperationTypeEntity, OperationTypeDTO> operationTypeConverter;
	
	@Autowired
	@Qualifier(value = "EquipmentConverter")
	private IConverter<EquipmentEntity, EquipmentDTO> equipmentConverter;
	
	
	
	@Override
	public OperationEntity convertFromDtoToEntity(OperationDTO dto) {

		OperationEntity entity = new OperationEntity();
		
		entity.setId(dto.getId());
		entity.setIndex(dto.getIndex());
		entity.setTime(dto.getTime());
		entity.setType(operationTypeConverter.convertFromDtoToEntity(dto.getType()));
		entity.setEquipment(equipmentConverter.convertFromDtoToEntity(dto.getEquipment()));
		
		return entity;
	}

	@Override
	public OperationDTO convertFromEntityToDto(OperationEntity entity) {
		
		OperationDTO dto = new OperationDTO();
		
		dto.setId(entity.getId());
		dto.setIndex(entity.getIndex());
		dto.setTime(entity.getTime());
		dto.setType(operationTypeConverter.convertFromEntityToDto(entity.getType()));
		dto.setEquipment(equipmentConverter.convertFromEntityToDto(entity.getEquipment()));
		
		return dto;
	}

}
