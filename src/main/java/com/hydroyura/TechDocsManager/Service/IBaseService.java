package com.hydroyura.TechDocsManager.Service;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

public interface IBaseService<D, E> {
	
	public Iterable<D> getAll(Specification<E> specification);
	public Iterable<D> getAll();
	public Optional<D> getById(Long id);
	public Optional<D> save(D dto);
	public void deleteById(Long id);
	
}
