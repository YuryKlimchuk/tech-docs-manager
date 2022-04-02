package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyStandartRateEntity;

@Entity
@Table(name = "standart")
public class StandartEntity extends SpecificationElementEntity {
	
	@Column(name = "standart")
	private String standart;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<AssemblyStandartRateEntity> rates = new ArrayList<>();
	
	public StandartEntity() {}

	public StandartEntity(String number, String name, String standart) {
		setName(name);
		setNumber(number);
		setStandart(standart);
		setUpdate(LocalDate.now());
	}
	
	
	
	public String getStandart() {
		return standart;
	}

	public void setStandart(String standart) {
		this.standart = standart;
	}

	public List<AssemblyStandartRateEntity> getRates() {
		return rates;
	}

	public void setRates(List<AssemblyStandartRateEntity> rates) {
		this.rates = rates;
	}
	
}
