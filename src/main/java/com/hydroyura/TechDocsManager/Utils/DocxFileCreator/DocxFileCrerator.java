package com.hydroyura.TechDocsManager.Utils.DocxFileCreator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

public class DocxFileCrerator {
	
	/*
	public static void createTable(XWPFDocument document, ArrayList<String> cellNames, FontStyle fontStyle) {
		
		if(cellNames == null || cellNames.size() < 1) throw new RuntimeException();
		
		// Создание таблицы (без указания размеров создается 1х1)
		XWPFTable table = document.createTable();
		XWPFTableRow titleRow = table.getRow(0);
		
		XWPFRun run1 = titleRow.getCell(0).addParagraph().createRun();

		fontStyle.setStyle(run1);
		
		
	} 
	*/
	
	
	public static XWPFTable createTableWithHeader(XWPFDocument document, String[] headers, FontStyle tableTitleFont) {
		
		if(headers == null || headers.length == 0) throw new RuntimeException("Ошибка при создании таблицы: пустой массив с заголовками");
		
		XWPFTable table = document.createTable();
		XWPFTableRow row = table.getRow(0);
		
		for(int i = 1; i < headers.length; i++ ) row.createCell();
		
		for(int i = 0; i < headers.length; i++) {
			XWPFRun run = row.getCell(i).addParagraph().createRun();
			tableTitleFont.setStyle(run).setText(headers[i]);
		}
		
		return table;
	}
	
	
    public static void mergeCellsHorizontal(XWPFTableRow row, int fromCell, int toCell) {      
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {    
            XWPFTableCell cell = row.getCell(cellIndex);    
            if (cellIndex == fromCell ) {    
                // The first merged cell is set with RESTART merge value    
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);    
            } else {    
                // Cells which join (merge) the first one, are set with CONTINUE    
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);    
            }    
        }    
    } 
	

}
