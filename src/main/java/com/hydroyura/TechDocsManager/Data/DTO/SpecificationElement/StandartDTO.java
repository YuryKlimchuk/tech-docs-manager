package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement;

import java.time.LocalDate;

public class StandartDTO extends SpecificationElementDTO {
	
	private String standart;
	
	
	
	public StandartDTO() {}
	
	public StandartDTO(String name, String number, String standart) {
		setName(name);
		setNumber(number);
		setStandart(standart);
		setUpdate(LocalDate.now());
	}

	
	
	public String getStandart() {
		return standart;
	}

	public void setStandart(String standart) {
		this.standart = standart;
	}	
}
