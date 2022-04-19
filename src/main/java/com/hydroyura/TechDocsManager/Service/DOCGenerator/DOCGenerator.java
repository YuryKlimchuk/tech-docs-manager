package com.hydroyura.TechDocsManager.Service.DOCGenerator;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Service.SpecificationFacade.ISpecificationFacade;
import com.hydroyura.TechDocsManager.Utils.DocxFileCreator.FontStyle;

@Component(value = "DOCGenerator")
public class DOCGenerator implements IDOCGenerator {

	@Override
	public XWPFDocument generate(ISpecificationFacade specificationFacade) {
		
		// Times New Roman
		System.out.println("Генерируем файл");
		
		// Создаем стили
		FontStyle titleFont = new FontStyle("Times New Roman", 14, true);
		FontStyle tableTitleFont = new FontStyle("Times New Roman", 12, true);
		FontStyle tableFont = new FontStyle("Times New Roman", 12, false);
		
		XWPFDocument document = new XWPFDocument();
		
		// begin - Заголовок
		XWPFParagraph titleParagraph = document.createParagraph();
		titleParagraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = titleParagraph.createRun();
		titleFont.setStyle(titleRun).setText("План производства - апрель 2022г.");
		// end --- Заголовок
		
		
		// begin - Заголовок таблицы продукция
		XWPFParagraph productsParagraph = document.createParagraph();
		productsParagraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun productsTitleRun = productsParagraph.createRun();
		tableTitleFont.setStyle(productsTitleRun).setText("Список продукции");
		// end --- Заголовок таблицы продукция
		
		
		
		XWPFTable productsTable = document.createTable();
		productsTable.setTableAlignment(TableRowAlign.CENTER);
		productsTable.setWidth("100%");
		
		
		// addNewTableCell == createCell
		XWPFTableRow productsTableTitleRow = productsTable.getRow(0);
		productsTableTitleRow.getCell(0).setText("№");
		productsTableTitleRow.createCell().setText("Тип");
		productsTableTitleRow.createCell().setText("Обозначение");
		productsTableTitleRow.createCell().setText("Потребитель");
		productsTableTitleRow.createCell().setText("Кол.");
		
		
		
		return document;
	}

}
