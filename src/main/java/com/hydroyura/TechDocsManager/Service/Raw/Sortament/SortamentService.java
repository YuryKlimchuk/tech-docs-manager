package com.hydroyura.TechDocsManager.Service.Raw.Sortament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Service(value = "SortamentService")
public class SortamentService extends AbstractSpecificationElementService<SortamentDTO, SortamentEntity, Long> {
	
	
	@Autowired
	public SortamentService(			
			@Qualifier(value = "SortamentRepository") JpaRepository<SortamentEntity, Long> repository,
			@Qualifier(value = "SortamentConverter") IConverter<SortamentEntity, SortamentDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
