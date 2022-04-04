package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Buy;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementMainController;

@Controller
@RequestMapping(value = "/specification-element/buy")
public class BuyMainController extends AbstractSpecificationElementMainController {
	
	@PostConstruct
	private void init() {
		this.KEY = "buy";
		this.KEY = "dd";
	}

}
