package com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.IBaseRawMainController;

public class AbstractBaseRawMainController implements IBaseRawMainController {
	
	private final String PATH = "/raw/";
	private final String HTML_MAIN = "main";
	private final String ULR_EDIT_LIST = "edit-list";
	private final String ULR_SHOW_LIST = "show-list";
	
	protected String label;
	

	@Override
	public String indexGET() {
		return PATH + label + "/" + HTML_MAIN;
	}

	@Override
	public String indexPOSTShow() {
		System.out.println("PRESSED SHOW BTN");
		return "redirect:" + PATH + label + "/" + ULR_SHOW_LIST;
	}

	@Override
	public String indexPOSTEdit() {
		System.out.println("PRESSED EDIT BTN");
		return "redirect:" + PATH + label + "/" + ULR_EDIT_LIST;
	}

}
