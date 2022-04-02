package com.hydroyura.TechDocsManager.Data.Converters.Product;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;

@Component(value = "ProductConverter")
public class ProductConverter implements IConverter<ProductEntity, ProductDTO> {

	@Override
	public ProductEntity convertFromDtoToEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		
		entity.setId(dto.getId());
		entity.setName(dto.getType());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}

	@Override
	public ProductDTO convertFromEntityToDto(ProductEntity entity) {
		
		ProductDTO dto = new ProductDTO();
		
		dto.setId(entity.getId());
		dto.setType(entity.getName());
		dto.setNumber(entity.getNumber());
		
		
		return dto;
	}

}
