package com.hydroyura.TechDocsManager.Service.SpecificationElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Service(value = "PartService")
public class PartService extends AbstractSpecificationElementService<PartDTO, PartEntity, Long>{
	
	@Autowired
	public PartService(			
			@Qualifier(value = "PartRepository") JpaRepository<PartEntity, Long> repository,
			@Qualifier(value = "PartConverter") IConverter<PartEntity, PartDTO> converter) {
		
		this.converter = converter;
		this.repository = repository;
	}

}
