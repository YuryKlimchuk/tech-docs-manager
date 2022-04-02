package com.hydroyura.TechDocsManager.Data.DTO.Route;

public class OperationTypeDTO {
	
	private long id;
	private String number;
	
	
	
	public OperationTypeDTO() {}
	
	public OperationTypeDTO(long id, String number) {
		setId(id);
		setNumber(number);
	}

	
	
	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
