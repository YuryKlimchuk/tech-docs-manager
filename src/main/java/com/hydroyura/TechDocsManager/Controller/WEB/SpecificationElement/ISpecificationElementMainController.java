package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface ISpecificationElementMainController {

	@GetMapping
	public String mainGET();
	
	@PostMapping(params = "btnShow")
	public String mainPOSTShow();
	
	@PostMapping(params = "btnEdit")
	public String mainPOSTEdit();
	
}
