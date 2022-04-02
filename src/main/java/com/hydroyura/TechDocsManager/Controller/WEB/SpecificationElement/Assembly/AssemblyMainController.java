package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Assembly;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementMainController;

@Controller
@RequestMapping(value = "/specification-element/assembly")
public class AssemblyMainController extends AbstractSpecificationElementMainController {
	
	@PostConstruct
	private void init() {
		this.KEY = "assembly";
	}

}
