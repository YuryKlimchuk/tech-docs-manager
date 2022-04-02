package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hydroyura.TechDocsManager.Service.Composite.ICompositeUtilities;

public class SpecificationRowAssembly extends BaseSpecificationRow {
	
	public SpecificationRowAssembly(long id, String label, long count, SpecificationRowType type) {
		super(id, label, count, type);
	}



	private List<ISpecificationRow> childRows = new ArrayList<>();
	//private String status;
	

	
	public void add(ISpecificationRow row) {
		this.childRows.add(row);
	}
	
	public List<ISpecificationRow> getChildRows() {
		return this.childRows;
	}

	@Override
	public Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> getValue() {
		
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map = new HashMap<>();

		for(ISpecificationRow row : childRows) {
			if(getCount() == -1) {
				ICompositeUtilities.mergeMap1(map, row.getValue());
			} else {
				ICompositeUtilities.mergeMap1(map, ICompositeUtilities.increaseMapCount2(row.getValue(), getCount()));
			}
		}
		
		return map;

	}


	

}
