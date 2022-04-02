package com.hydroyura.TechDocsManager.Data.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class BaseAbstractEntity extends IdAbstractEntity{

	@Column(name = "number", nullable = false, unique = true)
	@NotBlank
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "BaseAbstractEntity : [ID = " + getId() + "; number = " + getNumber() + "]";
	}
	
}
