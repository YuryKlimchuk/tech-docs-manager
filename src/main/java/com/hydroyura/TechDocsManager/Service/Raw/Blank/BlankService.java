package com.hydroyura.TechDocsManager.Service.Raw.Blank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "BlankService")
public class BlankService extends AbstractSpecificationElementService<BlankDTO, BlankEntity> {

	
	@Autowired
	public BlankService(			
			@Qualifier(value = "BlankRepository") BaseRepositoryWithSpecification<BlankEntity> repository,
			@Qualifier(value = "BlankConverter") IConverter<BlankEntity, BlankDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
