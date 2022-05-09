package com.hydroyura.TechDocsManager.Controller.WEB.Raw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/raw")
public class RawMainController {
	
	@GetMapping(value = "")
	public String mainGET() {
		return "/raw/main";
	}
	
	@PostMapping(value = "", params = "btnBlank")
	public String mainPOSTBlank() {
		return "redirect:/raw/blank";
	}
	
	@PostMapping(value = "", params = "btnMaterial")
	public String mainPOSTMaterial() {
		return "redirect:/raw/material";
	}
	
	@PostMapping(value = "", params = "btnMaterialType")
	public String mainPOSTMaterialType() {
		return "redirect:/raw/material-type";
	}
	
	@PostMapping(value = "", params = "btnSortament")
	public String mainPOSTSortament() {
		return "redirect:/raw/sortament";
	}
	
	@PostMapping(value = "", params = "btnSortamentType")
	public String mainPOSTSortamentType() {
		return "redirect:/raw/sortament-type";
	}

}
