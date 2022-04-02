package com.hydroyura.TechDocsManager.Service.SpecificationElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;

@Service(value = "VzkService")
public class VzkService extends AbstractSpecificationElementService<VzkDTO, VzkEntity, Long> {
	
	@Autowired
	public VzkService(			
			@Qualifier(value = "VzkRepository") JpaRepository<VzkEntity, Long> repository,
			@Qualifier(value = "VzkConverter") IConverter<VzkEntity, VzkDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}

}
