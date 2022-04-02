package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public class SpecificationRowVzk extends BaseSpecificationRow {
	
	


	public SpecificationRowVzk(long id, String label, long count, SpecificationRowType type) {
		super(id, label, count, type);
	}

	

	@Override
	public void accept(IVisitor visitor) {}



}
