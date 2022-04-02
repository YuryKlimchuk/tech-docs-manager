package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

@Component(value = "PartDTOFactory")
public class PartDTOFactory implements IDTOFactory<PartDTO> {

	@Override
	public PartDTO create() {
		return new PartDTO();
	}

}
