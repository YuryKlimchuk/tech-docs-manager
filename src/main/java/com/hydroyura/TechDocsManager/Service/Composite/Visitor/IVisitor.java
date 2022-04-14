package com.hydroyura.TechDocsManager.Service.Composite.Visitor;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;

public interface IVisitor {

	public void visit(ISpecificationRow row);
	public void reset();
	
}
