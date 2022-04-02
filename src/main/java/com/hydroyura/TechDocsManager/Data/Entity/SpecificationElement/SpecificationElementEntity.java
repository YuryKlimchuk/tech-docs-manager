package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.hydroyura.TechDocsManager.Data.Entity.ExtendedAbstractEntity;

@MappedSuperclass
public abstract class SpecificationElementEntity extends ExtendedAbstractEntity {
	
	@Column(name = "pdf")
	private String pdf;
	
	@Column(name = "update")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate update;

	public String getPdf() {
		return pdf;
	}

	
	public LocalDate getUpdate() {
		return update;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public void setUpdate(LocalDate update) {
		this.update = update;
	}

}
