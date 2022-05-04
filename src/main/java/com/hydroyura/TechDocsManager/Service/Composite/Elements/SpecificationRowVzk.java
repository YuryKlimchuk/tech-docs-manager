package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public class SpecificationRowVzk extends BaseSpecificationRow {
	
	private String name, number;

	public SpecificationRowVzk(long id, String label, long count, SpecificationRowType type, String name, String number) {
		super(id, label, count, type);
		this.name = name;
		this.number = number;
	}

	@Override
	public void accept(IVisitor visitor) {}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

}
