package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Part;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementMainController;

@Controller
@RequestMapping(value = "/specification-element/part")
public class PartMainController extends AbstractSpecificationElementMainController {
	
	@PostConstruct
	private void init() {
		this.KEY = "part";
	}

}
