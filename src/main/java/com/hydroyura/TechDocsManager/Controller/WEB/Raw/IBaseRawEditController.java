package com.hydroyura.TechDocsManager.Controller.WEB.Raw;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBaseRawEditController<T> {
	
	@GetMapping(value = "")
	public String editListGET(Model model);
	
	@PostMapping(value = "", params = "btnSearch")
	public String editListPOSTSearch(@RequestParam Map<String, String> searchParams);

}
