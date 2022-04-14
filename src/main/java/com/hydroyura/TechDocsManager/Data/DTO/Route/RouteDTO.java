package com.hydroyura.TechDocsManager.Data.DTO.Route;

import java.util.ArrayList;
import java.util.List;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

public class RouteDTO {
	
	private long id;
	private String number;
	private PartDTO part;
	
	//private List<OperationDTO> operations = new LinkedList<>();
	private List<BlankRateDTO> rates = new ArrayList<>();
	private BlankRateDTO selectedRate;
	
	public RouteDTO() {}
	
	

	
	
	public RouteDTO(long id, String number, PartDTO part) {
		this.id = id;
		this.number = number;
		this.part = part;
	}

	
	

	
	

	public BlankRateDTO getSelectedRate() {
		return selectedRate;
	}

	public void setSelectedRate(BlankRateDTO selectedRate) {
		this.selectedRate = selectedRate;
	}

	public List<BlankRateDTO> getRates() {
		return rates;
	}

	public void setRates(List<BlankRateDTO> rates) {
		this.rates = rates;
	}

	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public PartDTO getPart() {
		return part;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPart(PartDTO part) {
		this.part = part;
	}


	
	@Override
	public String toString() {
		return "RouteDTO = [id : " + getId() +
				", number : " + getNumber() + 
				", part : " + getPart().getNumber() + " " + getPart().getName() + " id = " + getPart().getId();

	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((part == null) ? 0 : part.hashCode());
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
		RouteDTO other = (RouteDTO) obj;
		if (id != other.id)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		return true;
	}


	
}
