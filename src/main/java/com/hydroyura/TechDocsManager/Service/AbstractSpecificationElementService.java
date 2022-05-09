package com.hydroyura.TechDocsManager.Service;

import java.util.HashSet;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;

public abstract class AbstractSpecificationElementService<D, E> implements IBaseService<D, E> {
	
	protected IConverter<E, D> converter;
	protected BaseRepositoryWithSpecification<E> repository;

	@Override
	public final Iterable<D> getAll(Specification<E> specification) {
		try {
			return converter.convertListFromEntityToDto(repository.findAll(specification));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return new HashSet<>();
	}
	
	@Override
	public final Iterable<D> getAll() {
		try {
			return converter.convertListFromEntityToDto(repository.findAll());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return new HashSet<>();
	}

	@Override
	public final Optional<D> getById(Long id) {
		Optional<E> entity = repository.findById(id);
		
		if(entity.isPresent()) return Optional.of(converter.convertFromEntityToDto(entity.get()));
		
		return Optional.empty();
	}
	
	@Override
	public final Optional<D> save(D dto) {
		E entity = converter.convertFromDtoToEntity(dto);
		
		try {
			return Optional.of(converter.convertFromEntityToDto(repository.save(entity)));
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	
	
}
