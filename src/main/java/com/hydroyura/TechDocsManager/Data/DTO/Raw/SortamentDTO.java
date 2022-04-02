package com.hydroyura.TechDocsManager.Data.DTO.Raw;

public class SortamentDTO {
	
	private long id;
	private SortamentTypeDTO type;
	private String number;
	private String standart;
	
	public SortamentDTO() {}
	
	public SortamentDTO(long id, SortamentTypeDTO type, String number, String standart) {
		setId(id);
		setType(type);
		setNumber(number);
		setStandart(standart);
	}

	public long getId() {
		return id;
	}
	public SortamentTypeDTO getType() {
		return type;
	}
	public String getNumber() {
		return number;
	}
	public String getStandart() {
		return standart;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setType(SortamentTypeDTO type) {
		this.type = type;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setStandart(String standart) {
		this.standart = standart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((standart == null) ? 0 : standart.hashCode());
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
		SortamentDTO other = (SortamentDTO) obj;
		if (id != other.id)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (standart == null) {
			if (other.standart != null)
				return false;
		} else if (!standart.equals(other.standart))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
