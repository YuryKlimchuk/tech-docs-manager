package com.hydroyura.TechDocsManager.Data.Entity.Product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.ExtendedAbstractEntity;

@Entity
@Table(name = "customer")
public class CustomerEntity extends ExtendedAbstractEntity {
	
	@ManyToMany(mappedBy = "customers")
	private List<ProductEntity> products = new ArrayList<>();
	
	
	public CustomerEntity() {}
	
	public CustomerEntity(String number, String country) {
		setNumber(number);
		setName(country);
	}
	
	

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
		
}
