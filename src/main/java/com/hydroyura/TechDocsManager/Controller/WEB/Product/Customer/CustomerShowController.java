package com.hydroyura.TechDocsManager.Controller.WEB.Product.Customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Service.Product.ICustomerService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "product/customer/show-list")
public class CustomerShowController extends AbstractController {

	private String LABEL = "product/customer";
	
	@Autowired @Qualifier(value = "CustomerService")
	private ICustomerService customerService;
	
	@GetMapping(value = "")
	public String listGET(Model model) {
		model.addAttribute("items", customerService.getAll());
		return LABEL + "/show_list";
	}
	
	@GetMapping(value = "/{id}")
	public String itemGET(Model model, RedirectAttributes redirectAttributes, @PathVariable(value = "id") String strId) {
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка 1");
			return "redirect:/" + LABEL + "/show-list";
		}
		
		Optional<CustomerDTO> customer = customerService.getById(id);
		if(customer.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка 2");
			return "redirect:/" + LABEL + "/show-list";
		}
		
		model.addAttribute("item", customer.get());
		model.addAttribute("products", customerService.getProducts(customer.get()));
		return LABEL + "/show_item";
	}
	
}
