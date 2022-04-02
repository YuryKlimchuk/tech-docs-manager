package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;

@Component(value = "BlankRateConverter")
public class BlankRateConverter implements IConverter<BlankRateEntity, BlankRateDTO> {
	
	@Autowired
	@Qualifier(value = "RouteConverter")
	private IConverter<RouteEntity, RouteDTO> routeConverter;
	
	@Autowired
	@Qualifier(value = "BlankConverter")
	private IConverter<BlankEntity, BlankDTO> blankConverter;

	@Override
	public BlankRateEntity convertFromDtoToEntity(BlankRateDTO dto) {
		
		BlankRateEntity entity = new BlankRateEntity();
		
		entity.setId(dto.getId());
		entity.setRate(dto.getRate());
		entity.setRoute(routeConverter.convertFromDtoToEntity(dto.getRoute()));
		entity.setBlank(blankConverter.convertFromDtoToEntity(dto.getBlank()));
		
		return entity;
	}

	@Override
	public BlankRateDTO convertFromEntityToDto(BlankRateEntity entity) {
		
		BlankRateDTO dto = new BlankRateDTO();

		dto.setBlank(blankConverter.convertFromEntityToDto(entity.getBlank()));
		dto.setId(entity.getId());
		dto.setRate(entity.getRate());
		
		return dto;
	}

}
