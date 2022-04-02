package com.hydroyura.TechDocsManager.Data.Entity.Route;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.BaseAbstractEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@Entity
@Table(name = "route")
public class RouteEntity extends BaseAbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "part_id")
	private PartEntity part;
	
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	private List<OperationEntity> operations = new ArrayList<>();
	
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BlankRateEntity> rates = new ArrayList<>();
	
	public RouteEntity() {}

	
	public RouteEntity(long id, String number) {
		setId(id);
		setNumber(number);
	}

	public PartEntity getPart() {
		return part;
	}

	public void setPart(PartEntity part) {
		this.part = part;
	}

	public List<BlankRateEntity> getRates() {
		return rates;
	}


	public void setRates(List<BlankRateEntity> rates) {
		this.rates = rates;
	}

	public List<OperationEntity> getOperations() {
		return operations;
	}


	public void setOperations(List<OperationEntity> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Route [ID = " + getId() + "; number = " + getNumber() + "; part = " + getPart().getNumber() + " " + getPart().getName() + "]"; 
	}
}
