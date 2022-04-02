package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/specification-element")
public class SpecificationElementsMainController {
	
	@GetMapping
	public String mainGET() {
		return "specification_element/main";
	}
	
	@PostMapping(params = "btnPart")
	public String mainPOSTPart() {
		return "redirect:/specification-element/part";
	}
	
	@PostMapping(params = "btnAssembly")
	public String mainPOSTAssembly() {
		return "redirect:/specification-element/assembly";
	}
	
	@PostMapping(params = "btnVzk")
	public String mainPOSTVzk() {
		return "redirect:/specification-element/vzk";
	}
	
	@PostMapping(params = "btnStandart")
	public String mainPOSTStandart() {
		return "redirect:/specification-element/standart";
	}
	
	@PostMapping(params = "btnBuy")
	public String mainPOSTBuy() {
		return "redirect:/specification-element/buy";
	}

}
