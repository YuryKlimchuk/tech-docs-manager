package com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy;

import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFTable;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Utils.DocxFileCreator.FontStyle;

public interface IStrategyContext {
	
	public void generateRow(XWPFTable table, Map<ISpecificationRow, Long> items);
	public void setFont(FontStyle fontStyle);
	public void setStrategy(IStrategy strategy);
	
}
