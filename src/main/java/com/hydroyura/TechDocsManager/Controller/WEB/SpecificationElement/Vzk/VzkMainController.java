package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Vzk;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementMainController;

@Controller
@RequestMapping(value = "/specification-element/vzk")
public class VzkMainController extends AbstractSpecificationElementMainController {
	
	@PostConstruct
	private void init() {
		this.KEY = "vzk";
	}

}
