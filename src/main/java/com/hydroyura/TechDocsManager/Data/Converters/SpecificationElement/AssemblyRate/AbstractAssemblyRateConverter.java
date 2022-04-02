package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.Factory.IAssemblyRateFactory;

public class AbstractAssemblyRateConverter<E1, D1> implements IConverter<AbstractAssemblyRateEntity<E1>, BaseAssemblyRateDTO<D1>> {
	
	@Autowired
	@Qualifier(value = "AssemblyConverter")
	private IConverter<AssemblyEntity, AssemblyDTO> assemblyConverter;
	
	protected IConverter<E1, D1> itemConverter;
	protected IAssemblyRateFactory<E1> factory;
	
	@Override
	public AbstractAssemblyRateEntity<E1> convertFromDtoToEntity(BaseAssemblyRateDTO<D1> dto) {
		
		AbstractAssemblyRateEntity<E1> entity = factory.getInstance();
		
		entity.setAssembly(assemblyConverter.convertFromDtoToEntity(dto.getAssembly()));
		entity.setItem(itemConverter.convertFromDtoToEntity(dto.getItem()));
		entity.setCount(dto.getCount());
		entity.setId(dto.getId());
		
		return entity;

	}

	@Override
	public BaseAssemblyRateDTO<D1> convertFromEntityToDto(AbstractAssemblyRateEntity<E1> entity) {
		
		BaseAssemblyRateDTO<D1> dto = new BaseAssemblyRateDTO<>();
		
		dto.setAssembly(assemblyConverter.convertFromEntityToDto(entity.getAssembly()));
		dto.setItem(itemConverter.convertFromEntityToDto(entity.getItem()));
		dto.setCount(entity.getCount());
		dto.setId(entity.getId());
		
		return dto;
	}



	
	
	
	

}
