package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowVzk;

@Component(value = "VzkStrategy")
public class VzkStrategy implements IStrategy {

	@Override
	public String[] getRowContent(ISpecificationRow row, long count, long index) {
		String[] rowsContext = new String[5];
		
		try {
			
			SpecificationRowVzk item = (SpecificationRowVzk) row;
			
			rowsContext[0] = String.valueOf(index);
			rowsContext[1] = item.getNumber();
			rowsContext[2] = item.getName();
			rowsContext[3] = String.valueOf(count);
			rowsContext[4] = "";
			
			System.out.println(rowsContext[1] + " " + rowsContext[2]);
			
		} catch (ClassCastException e) {
			System.out.println("Ошибка при приведении типов");
			e.printStackTrace();
		}
		
		return rowsContext;
	}
	

}
