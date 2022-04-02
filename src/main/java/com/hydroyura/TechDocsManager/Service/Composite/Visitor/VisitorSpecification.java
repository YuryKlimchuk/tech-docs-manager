package com.hydroyura.TechDocsManager.Service.Composite.Visitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowAssembly;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowBuy;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowStandart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowVzk;

@Component(value = "VisitorSpecification")
public class VisitorSpecification implements IVisitor{

	public List<SpecificationRowAssembly> assemblies = new ArrayList<>();
	public List<SpecificationRowPart> parts = new ArrayList<>();
	public List<SpecificationRowVzk> vzks = new ArrayList<>();
	public List<SpecificationRowStandart> standarts = new ArrayList<>();
	public List<SpecificationRowBuy> buys = new ArrayList<>();
	
	
	public void clear() {
		assemblies.clear();
		parts.clear();
		vzks.clear();
		standarts.clear();
		buys.clear();
	}
	
	
	@Override
	public void visit(ISpecificationRow row) {
		
		if(row instanceof SpecificationRowAssembly) assemblies.add((SpecificationRowAssembly) row);
		if(row instanceof SpecificationRowPart) parts.add((SpecificationRowPart) row);
		if(row instanceof SpecificationRowVzk) vzks.add((SpecificationRowVzk) row);
		if(row instanceof SpecificationRowStandart) standarts.add((SpecificationRowStandart) row);
		if(row instanceof SpecificationRowBuy) buys.add((SpecificationRowBuy) row);
		
	}

}
