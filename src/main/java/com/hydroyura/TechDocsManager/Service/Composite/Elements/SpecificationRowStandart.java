package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public class SpecificationRowStandart extends BaseSpecificationRow {

	private String name, number, standart;

	public SpecificationRowStandart(long id, String label, long count, SpecificationRowType type, String name, String number, String standart) {
		super(id, label, count, type);
		this.name = name;
		this.number = number;
		this.standart = standart;
	}



	@Override
	public void accept(IVisitor visitor) {}



	public String getName() {
		return name;
	}



	public String getNumber() {
		return number;
	}



	public String getStandart() {
		return standart;
	}

	

}
