package com.hydroyura.TechDocsManager.Service.Raw.Sortament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Service(value = "SortamentTypeService")
public class SortamentTypeService extends AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity> {
	
	
	@Autowired
	public SortamentTypeService(			
			@Qualifier(value = "SortamentTypeRepository") BaseRepositoryWithSpecification<SortamentTypeEntity> repository,
			@Qualifier(value = "SortamentTypeConverter") IConverter<SortamentTypeEntity, SortamentTypeDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
}
