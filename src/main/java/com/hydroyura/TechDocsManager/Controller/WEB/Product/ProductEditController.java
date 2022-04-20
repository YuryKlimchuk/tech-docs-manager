package com.hydroyura.TechDocsManager.Controller.WEB.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Service.Product.CustomerService;
import com.hydroyura.TechDocsManager.Service.Product.ProductService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;


@Controller
@RequestMapping(value = "/product/product")
public class ProductEditController extends AbstractController {

	@Autowired @Qualifier(value = "ProductService")
	private ProductService service;
	
	@Autowired @Qualifier(value = "CustomerService")
	private CustomerService customerService;
	
	@Autowired @Qualifier(value = "AssemblyService")
	private AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity, Long> assemblyService;
	
	
	private ProductDTO dto = null;
	private Map<Long, CustomerDTO> selectedCustomers = new HashMap<>();
	private Map<Long, AssemblyDTO> selectedAssemblies = new HashMap<>();
	
	
	@GetMapping(value = "/edit-list")
	public String editListGET(Model model) {
		model.addAttribute("dtos", service.getAll());
		return "product/product/edit_list";
	};
	
	@PostMapping(value = "/edit-list", params = "btnAdd")
	public String editListPOSTAdd() {
		return "redirect:/product/product/edit-list/add-1";
	};
	
	@PostMapping(value = "/edit-list", params = "btnDelete")
	public String editListPOSTDelete(@RequestParam(name = "btnDelete", defaultValue = "-1") String strId, RedirectAttributes redirectAttributes) {
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return "redirect:/product/product/edit-list";
		}
		return "redirect:/product/product/edit-list/accept-delete/" + strId;
	};
		
	
	@PostMapping(value = "/edit-list", params = "btnEdit")
	public String editListPOSTEdit(@RequestParam(name = "btnEdit", defaultValue = "-1") String strId, RedirectAttributes redirectAttributes) {
		return null;
	};
	
	
	
	@GetMapping(value = "/edit-list/add-1")
	public String editListAdd1GET(Model model) {
		
		if(dto == null) {
			dto = new ProductDTO();
		}
		
		model.addAttribute("dto", dto); 
		
		model.addAttribute("selectedCustomers", selectedCustomers);
		model.addAttribute("customers", customerService.getAll()); 
		
		model.addAttribute("selectedAssemblies", selectedAssemblies);
		model.addAttribute("assemblies", assemblyService.getAll());
		
		
		return "product/product/edit_list_add_1";
	};
	
	@PostMapping(value = "/edit-list/add-1", params = "btnAccept")
	public String editListAdd1POSTAccept(RedirectAttributes redirectAttributes,
			@ModelAttribute(name = "dto") ProductDTO returnDto) {
		
		String type = returnDto.getType();
		String number = returnDto.getNumber();
		
		System.out.println("NAME = " + type);
		System.out.println("NUMBER = " + number);
		
		if(type.equals("") || number.equals("")) {
			redirectAttributes.addFlashAttribute("msg", "Заполните все поля");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		dto.setNumber(number);
		dto.setType(type);
		
		
		return "redirect:/product/product/edit-list/add-1";
	}
	
	/*
	 * TO DO
	 * - Валидация сохраняемых данных
	 */
	@PostMapping(value = "/edit-list/add-1", params = "btnSave")
	public String editListAdd1POSTSave(RedirectAttributes redirectAttributes,
			@RequestParam(name = "addingCustomerId", required = true, defaultValue = "-1") String strId) {
		
		dto.setCustomers(selectedCustomers.values().stream().collect(Collectors.toList()));
		dto.setAssemblies(selectedAssemblies.values().stream().collect(Collectors.toList()));
		
		if(dto.getType().equals("") || dto.getNumber().equals("")) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось сохранить, заполните все поля");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		if(service.save(dto).isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось сохранить");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Успешно сохранено");
			dto = null;
			selectedAssemblies.clear();
			selectedCustomers.clear();
		}
		
		return "redirect:/product/product/edit-list/add-1";
	}
	
	
	@PostMapping(value = "/edit-list/add-1", params = "btnAddCustomer")
	public String editListAdd1POSTAddCustomer(RedirectAttributes redirectAttributes,
			@RequestParam(name = "addingCustomerId", required = true, defaultValue = "-1") String strId) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось добавить потребителя");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		selectedCustomers.put(id, customerService.getById(id).get());
		
		return "redirect:/product/product/edit-list/add-1";
	}
	

	@PostMapping(value = "/edit-list/add-1", params = "btnDeleteCustomer")
	public String editListAdd1POSTDeleteCustomer(RedirectAttributes redirectAttributes,
			@RequestParam(name = "btnDeleteCustomer", required = true, defaultValue = "-1") String strId) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось удалить потребителя");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		selectedCustomers.remove(id);
		
		return "redirect:/product/product/edit-list/add-1";
	}	
	

	@PostMapping(value = "/edit-list/add-1", params = "btnAddAssembly")
	public String editListAdd1POSTAddAssembly(RedirectAttributes redirectAttributes,
			@RequestParam(name = "addingAssemblyId", required = true, defaultValue = "-1") String strId) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось добавить сборочный чертеж");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		selectedAssemblies.put(id, assemblyService.getById(id).get());
		
		return "redirect:/product/product/edit-list/add-1";
	}
	
	@PostMapping(value = "/edit-list/add-1", params = "btnDeleteAssembly")
	public String editListAdd1POSTDeleteAssembly(RedirectAttributes redirectAttributes,
			@RequestParam(name = "btnDeleteAssembly", required = true, defaultValue = "-1") String strId) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Не удалось удалить сборочный чертеж");
			return "redirect:/product/product/edit-list/add-1";
		}
		
		selectedAssemblies.remove(id);
		
		return "redirect:/product/product/edit-list/add-1";
	}	
	

	@GetMapping(value = "/edit-list/accept-delete/{strId}")
	public String editListAcceptDeleteGET(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<ProductDTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return "redirect:/product/product/edit-list";
		}
		
		model.addAttribute("item", item.get());
		
		return "product/product/edit_list_accept_delete";
	}

	@PostMapping(value = "/edit-list/accept-delete/{strId}", params = "btnDelete")
	public String editListAcceptDeletePOSTDelete(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<ProductDTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
		} else {
			service.deleteById(id);
			redirectAttributes.addFlashAttribute("msg", "Удаление прошло успешно");
		}
		
		return "redirect:/product/product/edit-list";
	}
	
	@PostMapping(value = "/edit-list/accept-delete/{strId}", params = "btnCancel")
	public String editListAcceptDeletePOSTCancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Отмена удаления");
		return "redirect:/product/product/edit-list";
	}

	
}
