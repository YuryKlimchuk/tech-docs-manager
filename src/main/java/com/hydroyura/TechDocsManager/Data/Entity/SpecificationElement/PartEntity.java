package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyPartRateEntity;

/*
 * TO DO:
 *  - Добавить builder
 * 
 */

@Entity
@Table(name = "part")
public class PartEntity extends SpecificationElementEntity {
	
	public PartEntity(long id, String name, String number, String pdf, LocalDate update, String status) {
		setId(id);
		setName(name);
		setNumber(number);
		setPdf(pdf);
		setUpdate(update);
		setStatus(status);
	}
	
	
	@OneToMany(mappedBy = "part", cascade = CascadeType.REMOVE)
	private List<RouteEntity> routes = new ArrayList<>();
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL, targetEntity = AssemblyPartRateEntity.class)
	//private List<AbstractAssemblyRateEntity<PartEntity>> assemblyPartRates = new ArrayList<>();
	private List<AssemblyPartRateEntity> assemblyPartRates = new ArrayList<>();

	@Column(name = "status")
	private String status;
	
	public PartEntity() {}
	

	public List<RouteEntity> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteEntity> routes) {
		this.routes = routes;
	}



	public List<AssemblyPartRateEntity> getAssemblyPartRates() {
		return assemblyPartRates;
	}


	public void setAssemblyPartRates(List<AssemblyPartRateEntity> assemblyPartRates) {
		this.assemblyPartRates = assemblyPartRates;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Part [ID = " + getId() 
			+ "; number = " + getNumber()
			+ "; name = " + getName() 
			+ "; status = " + getStatus() 
			+ "; update = " + getUpdate() 
			+ "; routes = " + getRoutes() + "]"; 
	}
	
}
