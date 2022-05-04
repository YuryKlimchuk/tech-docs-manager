package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;

public interface IStrategy {	
	
	public String[] getRowContent(ISpecificationRow row, long count, long index);	
	
}
