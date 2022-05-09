package com.hydroyura.TechDocsManager.Service.Raw.Sortament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "SortamentService")
public class SortamentService extends AbstractSpecificationElementService<SortamentDTO, SortamentEntity> {
	
	
	@Autowired
	public SortamentService(			
			@Qualifier(value = "SortamentRepository") BaseRepositoryWithSpecification<SortamentEntity> repository,
			@Qualifier(value = "SortamentConverter") IConverter<SortamentEntity, SortamentDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
