package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ISpecificationElementController {
	
	@GetMapping(value = "/show-list")
	public String showListGET(Model model);
	
	@PostMapping(value = "/show-list", params = "btnSearch")
	public String showListPOSTSearch(@RequestParam Map<String, String> searchParams);
	
	@GetMapping(value = "/show-list/{id}")
	public String showListIdGET(@PathVariable(name = "id") String strId, Model model);
	
	@PostMapping(value = "/show-list/{id}", params = "btnDownloadPdf")
	public String  showListIdPostDownload(@PathVariable("id") String strId);
	
	@GetMapping(value = "/show-list/{id}/download-pdf")
	public ResponseEntity<byte[]> showListIdDownloadGET(@PathVariable("id") String strId);

}
