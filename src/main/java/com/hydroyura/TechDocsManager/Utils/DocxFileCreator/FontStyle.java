package com.hydroyura.TechDocsManager.Utils.DocxFileCreator;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class FontStyle {
	
	private String name;
	private int size;
	private boolean isBold;
	
	//private FontStyle() {}
	
	public FontStyle(String name, int size, boolean isBold) {
		this.name = name;
		this.size = size;
		this.isBold = isBold;
	}
	
	// Убрать возможную утечку памяти - Singltone
	public static FontStyle getDefaultInstance() {
		return new FontStyle("Times New Roman", 14, false);
	}
	
	public XWPFRun setStyle(XWPFRun run) {
		run.setBold(isBold);
		run.setFontFamily(name);
		run.setFontSize(size);
		return run;
	}
	
}
