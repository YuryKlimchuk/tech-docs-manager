package com.hydroyura.TechDocsManager.Data.Converters;

import java.util.List;
import java.util.stream.Collectors;

public interface IConverter<E, D> {
	
	public E convertFromDtoToEntity(D dto);
	public D convertFromEntityToDto(E entity);
	
	
	
	default List<E> convertListFromDtoToEntity(List<? extends D> dtos) {
		return dtos.stream()
				.map(this::convertFromDtoToEntity)
				.collect(Collectors.toList());
	}
	
	default List<D> convertListFromEntityToDto(List<? extends E> entities) {
		return entities.stream()
				.map(this::convertFromEntityToDto)
				.collect(Collectors.toList());
	}

}
