package com.hydroyura.TechDocsManager.Service.Raw.Blank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Service(value = "BlankService")
public class BlankService extends AbstractSpecificationElementService<BlankDTO, BlankEntity, Long> {

	
	@Autowired
	public BlankService(			
			@Qualifier(value = "BlankRepository") JpaRepository<BlankEntity, Long> repository,
			@Qualifier(value = "BlankConverter") IConverter<BlankEntity, BlankDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
