package com.hydroyura.TechDocsManager.Data.Converters.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Component(value = "RouteConverter")
public class RouteConverter implements IConverter<RouteEntity, RouteDTO> {

	@Autowired
	@Qualifier(value = "PartConverter")
	private IConverter<PartEntity, PartDTO> partConverter;
	
	@Override
	public RouteEntity convertFromDtoToEntity(RouteDTO dto) {
		
		RouteEntity entity = new RouteEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setPart(partConverter.convertFromDtoToEntity(dto.getPart()));
		
		return entity;
	}

	@Override
	public RouteDTO convertFromEntityToDto(RouteEntity entity) {
		
		RouteDTO dto = new RouteDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setPart(partConverter.convertFromEntityToDto(entity.getPart()));
		
		return dto;
	}

}
