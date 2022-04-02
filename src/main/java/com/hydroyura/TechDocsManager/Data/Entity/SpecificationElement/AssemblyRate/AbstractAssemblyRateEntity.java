package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

@MappedSuperclass
public class AbstractAssemblyRateEntity<T> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "assembly_id", nullable = false)
	private AssemblyEntity assembly;
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private T item;
	
	@Column(name = "count", nullable = false)
	private long count;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AssemblyEntity getAssembly() {
		return assembly;
	}

	public void setAssembly(AssemblyEntity assembly) {
		this.assembly = assembly;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
