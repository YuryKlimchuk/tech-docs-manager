package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public class SpecificationRowBuy extends BaseSpecificationRow {
	
	public SpecificationRowBuy(long id, String label, long count, SpecificationRowType type) {
		super(id, label, count, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IVisitor visitor) {}


}
