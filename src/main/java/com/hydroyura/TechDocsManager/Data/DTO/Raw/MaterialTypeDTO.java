package com.hydroyura.TechDocsManager.Data.DTO.Raw;

public class MaterialTypeDTO {
	
	private long id;
	private String number;
	
	public MaterialTypeDTO(){}

	public MaterialTypeDTO(long id, String number) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		MaterialTypeDTO other = (MaterialTypeDTO) obj;
		if (id != other.id)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	
	
	
	
}
