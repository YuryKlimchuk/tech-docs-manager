package com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement;

import java.time.LocalDate;

public final class PartDTO extends SpecificationElementDTO{

	private String status;

	public PartDTO(){
		setUpdate(LocalDate.now());
		//setName("НАИМЕНОВАНИЕ_ДЕТАЛИ");
		//setNumber("НОМЕР_ДЕТАЛИ");
		//setStatus("Проектирование");
	}

	public PartDTO(long id, String number, String name, String status) {
		setId(id);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((pdf == null) ? 0 : pdf.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((update == null) ? 0 : update.hashCode());
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
		PartDTO other = (PartDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (pdf == null) {
			if (other.pdf != null)
				return false;
		} else if (!pdf.equals(other.pdf))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (update == null) {
			if (other.update != null)
				return false;
		} else if (!update.equals(other.update))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PartDTO [id=" + id + ", number=" + number + ", name=" + name + ", status=" + status + ", pdf=" + pdf
				+ ", update=" + update + "]";
	}

}
