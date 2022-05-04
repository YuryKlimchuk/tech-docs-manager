package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;

@Component(value = "PartStrategy")
public class PartStrategy implements IStrategy {

	@Override
	public String[] getRowContent(ISpecificationRow row, long count, long index) {
		
		String[] rowsContext = new String[5];
		
		try {
			
			SpecificationRowPart part = (SpecificationRowPart) row;
			
			rowsContext[0] = String.valueOf(index);
			rowsContext[1] = part.getNumber();
			rowsContext[2] = part.getName();
			rowsContext[3] = String.valueOf(count);
			rowsContext[4] = part.getStatus();
			
		} catch (ClassCastException e) {
			System.out.println("Ошибка при приведении типов");
			e.printStackTrace();
		}
		
		return rowsContext;
	}

}
