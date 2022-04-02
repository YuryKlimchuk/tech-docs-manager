package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;

@Component(value = "MaterialTypeDTOFactory")
public class MaterialTypeDTOFactory implements IDTOFactory<MaterialTypeDTO> {

	@Override
	public MaterialTypeDTO create() {
		return new MaterialTypeDTO();
	}

}
