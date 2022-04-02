package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;

public abstract class BaseSpecificationRow implements ISpecificationRow {

	protected SpecificationRowType type;
	
	protected long id;
	protected long count;
	
	protected String label;
	
	
	
	
	public BaseSpecificationRow(long id, String label, long count, SpecificationRowType type) {
		this.id = id;
		this.count = count;
		this.type = type;
		this.label = label;
	}
	

	
	
	@Override
	public SpecificationRowType getType() {
		return type;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public long getCount() {
		return count;
	}

	@Override
	public String getLabel() {

		return label;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> getValue() {
		Map<ISpecificationRow, List<Long>> map1 = new HashMap<>();
		map1.put(this, Arrays.asList(getCount()));
		
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map2 = new HashMap<>();
		map2.put(getType(), map1);
		
		return map2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseSpecificationRow other = (BaseSpecificationRow) obj;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (type != other.type)
			return false;
		return true;
	}



	@Override
	public int compareTo(ISpecificationRow o) {
		return this.getLabel().compareTo(o.getLabel());
	}




	@Override
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
