package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyBuyRateEntity;

@Entity
@Table(name = "buy")
public class BuyEntity extends SpecificationElementEntity {
	
	private String manufacturer;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<AssemblyBuyRateEntity> rates = new ArrayList<>();
	
	public BuyEntity() {}

	
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<AssemblyBuyRateEntity> getRates() {
		return rates;
	}

	public void setRates(List<AssemblyBuyRateEntity> rates) {
		this.rates = rates;
	}

}
