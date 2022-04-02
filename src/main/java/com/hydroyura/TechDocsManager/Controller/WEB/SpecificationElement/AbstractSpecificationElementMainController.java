package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

public class AbstractSpecificationElementMainController implements ISpecificationElementMainController {

	protected String KEY;
	
	
	@Override
	public String mainGET() {
		return "specification_element/" + KEY + "/main";
	}

	@Override
	public String mainPOSTShow() {
		return "redirect:/specification-element/" + KEY + "/show-list";
	}

	@Override
	public String mainPOSTEdit() {
		return "redirect:/specification-element/" + KEY + "/edit-list";
	}
	
	

}
