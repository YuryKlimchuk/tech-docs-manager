package com.hydroyura.TechDocsManager.Controller.WEB;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController extends AbstractController {

	@GetMapping
	public String mainGET() {
		return "main";
	}
	
	
	
	
}
