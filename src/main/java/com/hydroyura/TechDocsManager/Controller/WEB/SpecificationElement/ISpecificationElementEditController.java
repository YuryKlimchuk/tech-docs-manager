package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ISpecificationElementEditController<DTO> {
	
	@GetMapping(value = "/edit-list")
	public String editListGET(Model model);
	
	@PostMapping(value = "/edit-list", params = "btnAdd")
	public String editListPOSTAdd();
	
	@PostMapping(value = "/edit-list", params = "btnDelete")
	public String editListPOSTDelete(@RequestParam(name = "btnDelete", defaultValue = "-1") String strId, RedirectAttributes redirectAttributes);
		
	@PostMapping(value = "/edit-list", params = "btnEdit")
	public String editListPOSTEdit(@RequestParam(name = "btnEdit", defaultValue = "-1") String strId, RedirectAttributes redirectAttributes);
	
	@PostMapping(value = "/edit-list", params = "btnSearch")
	public String editListPOSTSearch(@RequestParam Map<String, String> searchParams);
	
	@GetMapping(value = "/edit-list/{strId}/edit-1")
	public String editListEdit1GET(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model);
	
	@GetMapping(value = "/edit-list/accept-delete/{strId}")
	public String editListAcceptDeleteGET(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model);
	
	@PostMapping(value = "/edit-list/accept-delete/{strId}", params = "btnDelete")
	public String editListAcceptDeletePOSTDelete(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model);
	
	@PostMapping(value = "/edit-list/accept-delete/{strId}", params = "btnCancel")
	public String editListAcceptDeletePOSTCancel(RedirectAttributes redirectAttributes);
	
	@PostMapping(value = "/edit-list/{strId}/edit-1", params = "btnCancel")
	public String editListEdit1POSTCancel(RedirectAttributes redirectAttributes);
	
	@PostMapping(value = "/edit-list/{strId}/edit-1", params = "btnSave")
	public String editListEdit1POSTSave(@ModelAttribute(name = "item") DTO item, @PathVariable("strId") String strId, RedirectAttributes redirectAttributes);
	
	@GetMapping(value = "/edit-list/add-1")
	public String editListAdd1GET(Model model);
	
	@PostMapping(value = "/edit-list/add-1", params = "btnSave")
	public String editListAdd1PostSave(@ModelAttribute(name = "dto") DTO dto, @RequestParam(name = "draw") MultipartFile pdf, RedirectAttributes redirectAttributes);

}
