package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Utils.DocxFileCreator.FontStyle;

@Component(value = "DefaultStrategyContext")
public class DefaultStrategyContext implements IStrategyContext {

	private FontStyle fontStyle;
	private IStrategy strategy;
	
	@Override
	public void generateRow(XWPFTable table, Map<ISpecificationRow, Long> items) {
		
		if(fontStyle == null || strategy == null) throw new RuntimeException("Произошла ошибка: значения null у некоторых полей.");
		
		int index = 1;
		for (Map.Entry<ISpecificationRow, Long> item : items.entrySet()) {
			String[] rowContent = strategy.getRowContent(item.getKey(), item.getValue(), index);
			index++;
			
			XWPFTableRow row = table.createRow();
			for(int i = 0; i < rowContent.length; i++)
				fontStyle.setStyle(row.getCell(i).getParagraphArray(0).createRun()).setText(rowContent[i]);

		}
		
	}

	@Override
	public void setFont(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	@Override
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}
	
}