package com.hydroyura.TechDocsManager.Controller.WEB.Raw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface IBaseRawMainController {
	
	@GetMapping(value = "")
	public String indexGET();
	
	@PostMapping(value = "", params = "btnShow")
	public String indexPOSTShow();
	
	@PostMapping(value = "", params = "btnEdit")
	public String indexPOSTEdit();

}
