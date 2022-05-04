package com.hydroyura.TechDocsManager.Controller.WEB.Product.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;

@Controller
@RequestMapping(value = "/product/customer")
public class CustomerMainController extends AbstractController {
	
	private String LABEL = "product/customer";

	@GetMapping(value = "")
	public String indexGET() {
		return LABEL + "/main";
	}
	
	@PostMapping(value = "", params = "btnShow")
	public String indexPOSTShow() {
		return "redirect:/" + LABEL + "/show-list";
	}
	
	@PostMapping(value = "", params = "btnEdit")
	public String indexPOSTEdit() {
		return "redirect:/" + LABEL + "/edit-list";
	}
}
