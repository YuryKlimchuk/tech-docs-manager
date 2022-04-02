package com.hydroyura.TechDocsManager.Data.Converters.Product;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;

@Component(value = "CustomerConverter")
public class CustomerConverter implements IConverter<CustomerEntity, CustomerDTO> {

	@Override
	public CustomerEntity convertFromDtoToEntity(CustomerDTO dto) {
		CustomerEntity entity = new CustomerEntity();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public CustomerDTO convertFromEntityToDto(CustomerEntity entity) {
		
		CustomerDTO dto = new CustomerDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setNumber(entity.getNumber());
		
		
		return dto;
	}

}
