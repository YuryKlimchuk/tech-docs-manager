package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowStandart;

@Component(value = "StandartStrategy")
public class StandartStrategy implements IStrategy {

	@Override
	public String[] getRowContent(ISpecificationRow row, long count, long index) {
		String[] rowsContext = new String[5];
		
		try {
			
			SpecificationRowStandart item = (SpecificationRowStandart) row;
			
			rowsContext[0] = String.valueOf(index);
			rowsContext[1] = item.getName() + " " + item.getNumber();
			rowsContext[2] = item.getStandart();
			rowsContext[3] = String.valueOf(count);
			rowsContext[4] = "";
			
		} catch (ClassCastException e) {
			System.out.println("Ошибка при приведении типов");
			e.printStackTrace();
		}
		
		return rowsContext;
	}
	

}
