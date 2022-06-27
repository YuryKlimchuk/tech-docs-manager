package com.hydroyura.TechDocsManager.Controller.WEB.Raw;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IBaseRawShowController<T> {
	
	@GetMapping(value = "")
	public String showListGET(Model model);
	
	@PostMapping(value = "", params = "btnSearch")
	public String showListPOSTSearch(@RequestParam Map<String, String> searchParams);
	
	@GetMapping(value = "/{id}")
	public String showItemGET(@PathVariable(name = "id") String strId, RedirectAttributes redirectAttributes, Model model);
	
}
