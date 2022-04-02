package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;

@Component(value = "BuyDTOFactory")
public class BuyDTOFactory implements IDTOFactory<BuyDTO> {

	@Override
	public BuyDTO create() {
		return new BuyDTO();
	}

}
