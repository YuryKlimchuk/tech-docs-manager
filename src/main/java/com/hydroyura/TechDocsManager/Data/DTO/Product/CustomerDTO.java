package com.hydroyura.TechDocsManager.Data.DTO.Product;

public class CustomerDTO {

	private long id;
	private String number;
	private String name;
	
	public CustomerDTO() {}
	
	

	public CustomerDTO(String number, String country) {
		this.number = number;
		this.name = country;
	}



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
	
	
	
}
