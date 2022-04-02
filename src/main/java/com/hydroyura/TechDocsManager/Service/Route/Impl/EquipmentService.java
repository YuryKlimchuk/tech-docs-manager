package com.hydroyura.TechDocsManager.Service.Route.Impl;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Route.EquipmentEntity;
import com.hydroyura.TechDocsManager.Service.Route.IEquipmentService;

@Service(value = "EquipmentService")
public class EquipmentService implements IEquipmentService {

	@Autowired
	@Qualifier(value = "EquipmentConverter")
	private IConverter<EquipmentEntity, EquipmentDTO> converter;
	
	@Autowired
	@Qualifier(value = "EquipmentRepository")
	private JpaRepository<EquipmentEntity, Long> repository;
	
	
	
	@Override
	public Iterable<EquipmentDTO> getAll() {
		
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
	public Optional<EquipmentDTO> getById(long id) {
		return Optional.of(converter.convertFromEntityToDto(repository.findById(id).get()));
	}

	@Override
	public Optional<EquipmentDTO> save(EquipmentDTO dto) {
		EquipmentEntity entity = converter.convertFromDtoToEntity(dto);
		
		try {
			return Optional.of(converter.convertFromEntityToDto(repository.save(entity)));
		} catch (DataAccessException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
