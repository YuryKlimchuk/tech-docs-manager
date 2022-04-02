package com.hydroyura.TechDocsManager.Service.SpecificationElement;

import java.util.HashSet;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;

public abstract class AbstractSpecificationElementService<D, E, I> implements IBaseService<D, E, I> {
	
	protected IConverter<E, D> converter;
	protected JpaRepository<E, I> repository;

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
	public final Optional<D> getById(I id) {
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
	public void deleteById(I id) {
		repository.deleteById(id);
	}
	
	
	
}
