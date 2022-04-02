package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;

@Component(value = "VzkDTOFactory")
public class VzkDTOFactory implements IDTOFactory<VzkDTO> {

	@Override
	public VzkDTO create() {
		return new VzkDTO();
	}

}
