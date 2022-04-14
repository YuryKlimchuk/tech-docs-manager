package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;

public class AssemblyDTO extends SpecificationElementDTO {
	
	private String status;

	public AssemblyDTO() {
		setUpdate(LocalDate.now());
	}

	public AssemblyDTO(String number, String name, String status) {
		setName(name);
		setNumber(number);
		setStatus(status);
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
