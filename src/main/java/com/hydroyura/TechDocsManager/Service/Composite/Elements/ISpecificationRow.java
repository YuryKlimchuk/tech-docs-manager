package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import java.util.List;
import java.util.Map;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public interface ISpecificationRow extends Comparable<ISpecificationRow> {
	
	public SpecificationRowType getType();
	public Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> getValue();
	public void accept(IVisitor visitor);
	public long getId();
	public long getCount();
	public void setCount(long count);
	public String getLabel();
	
}


