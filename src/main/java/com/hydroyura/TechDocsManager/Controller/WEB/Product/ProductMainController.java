package com.hydroyura.TechDocsManager.Controller.WEB.Product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;

@Controller
@RequestMapping(value = "/product")
public class ProductMainController extends AbstractController {
	
	@GetMapping(value = "")
	public String indexGET() {
		return "product/main";
	}
	
	@PostMapping(value = "", params = "btnProduct")
	public String indexPOSTProduct() {
		return "redirect:/product/product";
	}
	
	@PostMapping(value = "", params = "btnCustomer")
	public String indexPOSTCustomer() {
		return "redirect:/product/customer";
	}

}
