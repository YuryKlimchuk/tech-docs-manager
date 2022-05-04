package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public class SpecificationRowBuy extends BaseSpecificationRow {
	
	private String name, number, manufacturer;
	
	public SpecificationRowBuy(long id, String label, long count, SpecificationRowType type, String name, String number, String manufacturer) {
		super(id, label, count, type);
		this.name = name;
		this.number = number;
		this.manufacturer = manufacturer;
	}

	@Override
	public void accept(IVisitor visitor) {}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	
	

}
