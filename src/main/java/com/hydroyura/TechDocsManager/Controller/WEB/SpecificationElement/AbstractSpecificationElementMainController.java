package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;

public abstract class AbstractSpecificationElementMainController extends AbstractController implements ISpecificationElementMainController {

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
