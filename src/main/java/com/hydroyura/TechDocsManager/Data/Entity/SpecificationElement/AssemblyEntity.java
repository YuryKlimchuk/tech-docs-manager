package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyPartRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyStandartRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AssemblyVzkRateEntity;

@Entity
@Table(name = "asssembly")
public class AssemblyEntity extends SpecificationElementEntity {

	@Column(name = "status")
	private String status;

	
	
	@OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL)
	private List<AssemblyPartRateEntity> assemblyPartRates = new ArrayList<>();
	
	@OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL)
	private List<AssemblyVzkRateEntity> assemblyVzkRates = new ArrayList<>();
	
	@OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL)
	private List<AssemblyStandartRateEntity> assemblyStandartRates = new ArrayList<>();
	
	@OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL)
	private List<AssemblyAssemblyRateEntity> assemblyAssemblyRates = new ArrayList<>();
	
	
	@ManyToMany(mappedBy = "assemblies")
	private List<ProductEntity> products = new ArrayList<>();
	
	
	public AssemblyEntity() {
		setUpdate(LocalDate.now());
	}


	public AssemblyEntity(String number, String name, String status) {
		setName(name);
		setNumber(number);
		setStatus(status);
		setUpdate(LocalDate.now());
	}
	
	
	public String getStatus() {
		return status;
	}


	public List<AssemblyPartRateEntity> getAssemblyPartRates() {
		return assemblyPartRates;
	}


	public List<AssemblyVzkRateEntity> getAssemblyVzkRates() {
		return assemblyVzkRates;
	}

	public List<AssemblyStandartRateEntity> getAssemblyStandartRates() {
		return assemblyStandartRates;
	}

	public List<AssemblyAssemblyRateEntity> getAssemblyAssemblyRates() {
		return assemblyAssemblyRates;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAssemblyPartRates(List<AssemblyPartRateEntity> assemblyPartRates) {
		this.assemblyPartRates = assemblyPartRates;
	}

	public void setAssemblyVzkRates(List<AssemblyVzkRateEntity> assemblyVzkRates) {
		this.assemblyVzkRates = assemblyVzkRates;
	}

	public void setAssemblyStandartRates(List<AssemblyStandartRateEntity> assemblyStandartRates) {
		this.assemblyStandartRates = assemblyStandartRates;
	}

	public void setAssemblyAssemblyRates(List<AssemblyAssemblyRateEntity> assemblyAssemblyRates) {
		this.assemblyAssemblyRates = assemblyAssemblyRates;
	}


	public List<ProductEntity> getProducts() {
		return products;
	}


	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}



}
