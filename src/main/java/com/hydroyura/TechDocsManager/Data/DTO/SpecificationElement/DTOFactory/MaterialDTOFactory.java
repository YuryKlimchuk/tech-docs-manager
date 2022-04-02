package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;

@Component(value = "MaterialDTOFactory")
public class MaterialDTOFactory implements IDTOFactory<MaterialDTO> {

	@Override
	public MaterialDTO create() {
		return new MaterialDTO();
	}

}
