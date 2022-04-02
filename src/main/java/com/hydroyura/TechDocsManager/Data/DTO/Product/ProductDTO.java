package com.hydroyura.TechDocsManager.Data.DTO.Product;

import java.util.ArrayList;
import java.util.List;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

public class ProductDTO {

	private long id;
	private String type;
	private String number;
	
	private List<CustomerDTO> customers = new ArrayList<>();
	private List<AssemblyDTO> assemblies = new ArrayList<>();
	
	private AssemblyDTO selectedAssembly;
	
	public ProductDTO() {}
	
	public ProductDTO(String number, String type) {
		setNumber(number);
		setType(type);
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}

	public List<AssemblyDTO> getAssemblies() {
		return assemblies;
	}

	public void setAssemblies(List<AssemblyDTO> assemblies) {
		this.assemblies = assemblies;
	}

	
	public AssemblyDTO getSelectedAssembly() {
		return selectedAssembly;
	}

	public void setSelectedAssembly(AssemblyDTO selectedAssembly) {
		this.selectedAssembly = selectedAssembly;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (id != other.id)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
		
}
