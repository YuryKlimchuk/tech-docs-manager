package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public abstract class SpecificationElementDTO {
	
	protected long id;
	protected String number;
	protected String name;
	protected byte[] pdf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate update = LocalDate.now();

	public SpecificationElementDTO() {}
	
	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getUpdate() {
		return update;
	}

	public void setUpdate(LocalDate update) {
		this.update = update;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

}
