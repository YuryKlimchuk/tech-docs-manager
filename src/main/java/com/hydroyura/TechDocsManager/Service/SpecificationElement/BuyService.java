package com.hydroyura.TechDocsManager.Service.SpecificationElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;

@Service(value = "BuyService")
public class BuyService extends AbstractSpecificationElementService<BuyDTO, BuyEntity, Long> {
	
	@Autowired
	public BuyService(			
			@Qualifier(value = "BuyRepository") JpaRepository<BuyEntity, Long> repository,
			@Qualifier(value = "BuyConverter") IConverter<BuyEntity, BuyDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}

}
