package com.hydroyura.TechDocsManager.Service;

import java.util.Optional;

public interface IBaseService<D, I> {
	
	public Iterable<D> getAll();
	public Optional<D> getById(I id);
	public Optional<D> save(D dto);
	public void deleteById(I id);
	
}
