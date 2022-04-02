package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;

@Component(value = "BlankDTOFactory")
public class BlankDTOFactory implements IDTOFactory<BlankDTO> {

	@Override
	public BlankDTO create() {
		return new BlankDTO();
	}

}
