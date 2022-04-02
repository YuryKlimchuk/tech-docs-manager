package com.hydroyura.TechDocsManager.Service.Route.Impl;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationTypeEntity;
import com.hydroyura.TechDocsManager.Service.Route.IOperationTypeService;

@Service(value = "OperationTypeService")
public class OperationTypeService implements IOperationTypeService {

	@Autowired
	@Qualifier(value = "OperationTypeConverter")
	private IConverter<OperationTypeEntity, OperationTypeDTO> converter;
	
	@Autowired
	@Qualifier(value = "OperationTypeRepository")
	private JpaRepository<OperationTypeEntity, Long> repository;
	
	
	
	@Override
	public Iterable<OperationTypeDTO> getAll() {
		
		try {
			return converter.convertListFromEntityToDto(repository.findAll());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return new HashSet<>();
	}

	/*
	 * TO DO
	 * - добавить исключение
	 */
	@Override
	public Optional<OperationTypeDTO> getById(long id) {
		return Optional.of(converter.convertFromEntityToDto(repository.findById(id).get()));
	}

	
	@Override
	public Optional<OperationTypeDTO> save(OperationTypeDTO dto) {
		OperationTypeEntity entity = converter.convertFromDtoToEntity(dto);
		
		try {
			return Optional.of(converter.convertFromEntityToDto(repository.save(entity)));
		} catch (DataAccessException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
