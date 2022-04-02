package com.hydroyura.TechDocsManager.Data.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class ExtendedAbstractEntity extends BaseAbstractEntity {

	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
