package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

public class BaseAssemblyRateDTO<T> {

	protected long id;
	protected AssemblyDTO assembly;
	protected T item;
	protected long count;
	
	
	
	public BaseAssemblyRateDTO(AssemblyDTO assembly, T item, long count) {
		setAssembly(assembly);
		setItem(item);
		setCount(count);
	}
	
	public BaseAssemblyRateDTO() {}

	
	
	public long getId() {
		return id;
	}

	public AssemblyDTO getAssembly() {
		return assembly;
	}

	public T getItem() {
		return item;
	}

	public long getCount() {
		return count;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAssembly(AssemblyDTO assembly) {
		this.assembly = assembly;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return item.toString();
	}

}
