package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;

@Component(value = "SortamentTypeDTOFactory")
public class SortamentTypeDTOFactory implements IDTOFactory<SortamentTypeDTO> {

	@Override
	public SortamentTypeDTO create() {
		return new SortamentTypeDTO();
	}

}
