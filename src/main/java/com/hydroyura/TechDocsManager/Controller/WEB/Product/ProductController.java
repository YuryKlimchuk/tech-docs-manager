package com.hydroyura.TechDocsManager.Controller.WEB.Product;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Service.Product.ProductService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "/product/product")
public class ProductController {
	
	@Autowired @Qualifier(value = "ProductService")
	private ProductService service;

	@GetMapping(value = "/show-list")
	public String showListGET(Model model) {
		model.addAttribute("dtos", service.getAll());
		return "product/product/show_list";
	}
	
	@GetMapping(value = "/show-list/{id}")
	public String showListIdGET(@PathVariable(name = "id") String strId, Model model) {
		
		Optional<ProductDTO> dto = service.getById(IDValidator.convertFromStringToLong(strId));
		if(dto.isEmpty()) return "redirect:/product/product/show-list";
		
		model.addAttribute("dto", dto.get());
		model.addAttribute("customers", service.getCustomersByProductId(dto.get().getId()));
		
		Iterable<AssemblyDTO> assemblies = service.getAssembliesByProductId(dto.get().getId());
		System.out.println(StreamSupport.stream(assemblies.spliterator(), false).count());
		model.addAttribute("assemblies", assemblies);
		
		return "product/product/show_item";
	}
	
}
