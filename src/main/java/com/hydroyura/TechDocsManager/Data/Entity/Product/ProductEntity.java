package com.hydroyura.TechDocsManager.Data.Entity.Product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.ExtendedAbstractEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

@Entity
@Table(name = "product")
public class ProductEntity extends ExtendedAbstractEntity {
	
	
	@ManyToMany
	@JoinTable(
			name = "product_customer",
			joinColumns = {@JoinColumn(columnDefinition = "product_id")},
			inverseJoinColumns = {@JoinColumn(columnDefinition = "customer_id")})
	private List<CustomerEntity> customers = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			name = "product_assembly",
			joinColumns = {@JoinColumn(columnDefinition = "product_id")},
			inverseJoinColumns = {@JoinColumn(columnDefinition = "assembly_id")})
	private List<AssemblyEntity> assemblies = new ArrayList<>();
	
	
	public ProductEntity() {}

	public ProductEntity(String number, String productType) {
		setNumber(number);
		setName(productType);
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}
	
	public void addCustomer(CustomerEntity customer) {
		this.customers.add(customer);
	}

	public List<AssemblyEntity> getAssemblies() {
		return assemblies;
	}

	public void setAssemblies(List<AssemblyEntity> assemblies) {
		this.assemblies = assemblies;
	}
	
	public void addAssembly(AssemblyEntity assembly) {
		this.assemblies.add(assembly);
	}


}