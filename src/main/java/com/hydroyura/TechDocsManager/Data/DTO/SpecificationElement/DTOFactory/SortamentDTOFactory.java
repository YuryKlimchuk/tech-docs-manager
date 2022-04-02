package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;

@Component(value = "SortamentDTOFactory")
public class SortamentDTOFactory implements IDTOFactory<SortamentDTO> {

	@Override
	public SortamentDTO create() {
		return new SortamentDTO();
	}

}
