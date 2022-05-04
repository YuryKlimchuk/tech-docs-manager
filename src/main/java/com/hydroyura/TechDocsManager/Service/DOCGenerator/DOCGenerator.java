package com.hydroyura.TechDocsManager.Service.DOCGenerator;

import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy.IStrategy;
import com.hydroyura.TechDocsManager.Service.DOCGenerator.Strategy.IStrategyContext;
import com.hydroyura.TechDocsManager.Service.SpecificationFacade.ISpecificationFacade;
import com.hydroyura.TechDocsManager.Utils.DocxFileCreator.DocxFileCrerator;
import com.hydroyura.TechDocsManager.Utils.DocxFileCreator.FontStyle;

@Component(value = "DOCGenerator")
public class DOCGenerator implements IDOCGenerator {
	
	@Autowired @Qualifier(value = "PartStrategy")
	private IStrategy partStrategy;
	
	@Autowired @Qualifier(value = "StandartStrategy")
	private IStrategy standartStrategy;
	
	@Autowired @Qualifier(value = "BuyStrategy")
	private IStrategy buyStrategy;
	
	@Autowired @Qualifier(value = "VzkStrategy")
	private IStrategy vzkStrategy;
	
	
	@Autowired @Qualifier(value = "DefaultStrategyContext")
	private IStrategyContext strategyContext;

	@Override
	public XWPFDocument generateSpecification(ISpecificationFacade specificationFacade) {
		
		// Times New Roman
		System.out.println("Генерируем файл");
		
		// Создаем стили
		FontStyle titleFont = new FontStyle("Times New Roman", 14, true);
		FontStyle tableTitleFont = new FontStyle("Times New Roman", 12, true);
		FontStyle tableContextFont = new FontStyle("Times New Roman", 10, false);
		
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
		
		
		
		XWPFTableRow productsTableTitleRow = productsTable.getRow(0);
		productsTableTitleRow.getCell(0).setText("№");
		productsTableTitleRow.createCell().setText("Тип");
		productsTableTitleRow.createCell().setText("Обозначение");
		productsTableTitleRow.createCell().setText("Потребитель");
		productsTableTitleRow.createCell().setText("Кол.");
		
		
		specificationFacade.getProducts().forEach(new BiConsumer<ProductDTO, Long>() {

			@Override
			public void accept(ProductDTO t, Long u) {
				XWPFTableRow row = productsTable.createRow();
				row.getCell(0).setText("+");
				row.getCell(1).setText(t.getType());
				row.getCell(2).setText(t.getNumber());
				row.getCell(3).setText("+");
				row.getCell(4).setText(String.valueOf(u));
			}
			
		});
		
		
		/// Делаем спецуху!!!!
		
		// begin - Заголовок таблицы спецификация
		XWPFParagraph specificationParagraph = document.createParagraph();
		specificationParagraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun specificationTitleRun = specificationParagraph.createRun();
		tableTitleFont.setStyle(specificationTitleRun).setText("Развернутая спецификация");
		// end --- Заголовок таблицы спецификация
		
		
		// таблича спецификации
		XWPFTable specificationTable = DocxFileCrerator.createTableWithHeader(
				document, 
				new String[] {"№", "Обозначение", "Наименование", "Кол.", "Примечание"}, 
				tableTitleFont);
		specificationTable.setTableAlignment(TableRowAlign.CENTER);
		specificationTable.setWidth("100%");
		
		

		
		//--------------------------------------------------------------------------
		Map<ISpecificationRow, Long> parts = specificationFacade.getSpecificationElement(SpecificationRowType.PART);
		if(!parts.isEmpty()) {
			XWPFTableRow partTitleRow = specificationTable.createRow();
			DocxFileCrerator.mergeCellsHorizontal(partTitleRow, 0, 4);
			tableTitleFont.setStyle(partTitleRow.getCell(0).getParagraphArray(0).createRun()).setText("Детали");
			
			strategyContext.setFont(tableContextFont);
			strategyContext.setStrategy(partStrategy);
			strategyContext.generateRow(specificationTable, parts);	
		}

		//--------------------------------------------------------------------------
		Map<ISpecificationRow, Long> standarts = specificationFacade.getSpecificationElement(SpecificationRowType.STANDART);
		if(!standarts.isEmpty()) {
			XWPFTableRow partTitleRow = specificationTable.createRow();
			DocxFileCrerator.mergeCellsHorizontal(partTitleRow, 0, 4);
			tableTitleFont.setStyle(partTitleRow.getCell(0).getParagraphArray(0).createRun()).setText("Стандартные изделия");
			
			strategyContext.setFont(tableContextFont);
			strategyContext.setStrategy(standartStrategy);
			strategyContext.generateRow(specificationTable, standarts);	
		}
		
		//--------------------------------------------------------------------------
		Map<ISpecificationRow, Long> buys = specificationFacade.getSpecificationElement(SpecificationRowType.BUY);
		if(!buys.isEmpty()) {
			XWPFTableRow partTitleRow = specificationTable.createRow();
			DocxFileCrerator.mergeCellsHorizontal(partTitleRow, 0, 4);
			tableTitleFont.setStyle(partTitleRow.getCell(0).getParagraphArray(0).createRun()).setText("Покупные изделия");
			
			strategyContext.setFont(tableContextFont);
			strategyContext.setStrategy(buyStrategy);
			strategyContext.generateRow(specificationTable, buys);	
		}
		
		//--------------------------------------------------------------------------
		Map<ISpecificationRow, Long> vzks = specificationFacade.getSpecificationElement(SpecificationRowType.VZK);
		if(!vzks.isEmpty()) {
			XWPFTableRow partTitleRow = specificationTable.createRow();
			DocxFileCrerator.mergeCellsHorizontal(partTitleRow, 0, 4);
			tableTitleFont.setStyle(partTitleRow.getCell(0).getParagraphArray(0).createRun()).setText("Взаимозаменяемые комплектующие");
			
			strategyContext.setFont(tableContextFont);
			strategyContext.setStrategy(vzkStrategy);
			strategyContext.generateRow(specificationTable, vzks);	
		}
		
		return document;
	}

}
