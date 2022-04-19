package com.hydroyura.TechDocsManager.Utils.DocxFileCreator;

import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DocxFileCrerator {
	
	
	public static void createTable(XWPFDocument document, ArrayList<String> cellNames, FontStyle fontStyle) {
		
		if(cellNames == null || cellNames.size() < 1) throw new RuntimeException();
		
		// Создание таблицы (без указания размеров создается 1х1)
		XWPFTable table = document.createTable();
		XWPFTableRow titleRow = table.getRow(0);
		
		XWPFRun run1 = titleRow.getCell(0).addParagraph().createRun();

		fontStyle.setStyle(run1);
		
		
	} 
	

}
