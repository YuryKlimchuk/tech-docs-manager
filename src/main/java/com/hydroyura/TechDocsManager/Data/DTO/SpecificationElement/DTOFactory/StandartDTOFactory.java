package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;

@Component(value = "StandartDTOFactory")
public class StandartDTOFactory implements IDTOFactory<StandartDTO> {

	@Override
	public StandartDTO create() {
		return new StandartDTO();
	}

}
