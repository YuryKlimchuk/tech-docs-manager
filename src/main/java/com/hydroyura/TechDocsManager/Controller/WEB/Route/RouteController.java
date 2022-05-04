package com.hydroyura.TechDocsManager.Controller.WEB.Route;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "/route")
public class RouteController extends AbstractController {
	
	@Autowired
	@Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	
	
	
	@GetMapping
	public String mainGET() {
		return "route/main";
	}
	
	@PostMapping(params = "btnShow")
	public String mainPOSTShow() {
		return "redirect:/route/show-list";
	}

	@PostMapping(params = "btnEdit")
	public String mainPOSTEdit() {
		return "redirect:/route/edit-list";
	}
	
	@GetMapping(value = "/show-list")
	public String showListGET(Model model) {
		model.addAttribute("routes", routeService.getAll());
		return "route/show_list";
	}
	
	@GetMapping(value = "/show-list/{id}")
	public String showListIdGET(@PathVariable(name = "id") String strId, Model model) {
		
		Optional<RouteDTO> routeDTO = routeService.getById(IDValidator.convertFromStringToLong(strId));
		
		if(routeDTO.isEmpty()) return "redirect:/route/show-list";
		
		model.addAttribute("routeDTO", routeDTO.get());
		model.addAttribute("blankRateDTOs", routeService.getBlankRates(routeDTO.get().getId()));
		model.addAttribute("operationDTOs", routeService.getOperations(routeDTO.get().getId()));
		
		
		return "route/show_item";
	}

}
