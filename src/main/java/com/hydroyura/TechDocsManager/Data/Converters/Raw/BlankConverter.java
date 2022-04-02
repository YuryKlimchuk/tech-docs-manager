package com.hydroyura.TechDocsManager.Data.Converters.Raw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;

@Component(value = "BlankConverter")
public class BlankConverter implements IConverter<BlankEntity, BlankDTO> {
	
	@Autowired
	@Qualifier(value = "MaterialConverter")
	private IConverter<MaterialEntity, MaterialDTO> materialConverter;

	@Autowired
	@Qualifier(value = "SortamentConverter")
	private IConverter<SortamentEntity, SortamentDTO> sortamentConverter;
	
	
	
	@Override
	public BlankEntity convertFromDtoToEntity(BlankDTO dto) {
		
		BlankEntity entity = new BlankEntity();
		
		entity.setId(dto.getId());
		entity.setMaterial(materialConverter.convertFromDtoToEntity(dto.getMaterial()));
		entity.setSortament(sortamentConverter.convertFromDtoToEntity(dto.getSortament()));
		
		return entity;
	}

	@Override
	public BlankDTO convertFromEntityToDto(BlankEntity entity) {
		
		BlankDTO dto = new BlankDTO();
		
		dto.setId(entity.getId());
		dto.setMaterial(materialConverter.convertFromEntityToDto(entity.getMaterial()));
		dto.setSortament(sortamentConverter.convertFromEntityToDto(entity.getSortament()));
		
		return dto;
	}

}
