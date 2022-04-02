package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement;

public class BuyDTO extends SpecificationElementDTO {
	
	private String manufacturer;
	
	public BuyDTO() {}
	
	public BuyDTO(String name, String number, String manufacturer) {
		setName(name);
		setNumber(number);
		setManufacturer(manufacturer);
	}

	
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Override
	public String toString() {
		return "BuyDTO {ID = " + getId() + "}"; 
	}

}
