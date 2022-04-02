package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

@Component(value = "AssemblyDTOFactory")
public class AssemblyDTOFactory implements IDTOFactory<AssemblyDTO> {

	@Override
	public AssemblyDTO create() {
		return new AssemblyDTO();
	}

}
