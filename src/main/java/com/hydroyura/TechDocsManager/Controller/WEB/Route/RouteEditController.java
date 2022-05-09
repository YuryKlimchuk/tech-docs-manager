package com.hydroyura.TechDocsManager.Controller.WEB.Route;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
//import com.hydroyura.TechDocsManager.Service.Raw.IBlankService;
import com.hydroyura.TechDocsManager.Service.Route.IEquipmentService;
import com.hydroyura.TechDocsManager.Service.Route.IOperationTypeService;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;
import com.hydroyura.TechDocsManager.Service.RouteCreatorFacade.IRouteCreatorFacade;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "/route")
public class RouteEditController extends AbstractController {
	
	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity> partService;
	
	@Autowired @Qualifier(value = "BlankService")
	private AbstractSpecificationElementService<BlankDTO, BlankEntity> blankService;
	
	@Autowired @Qualifier(value = "EquipmentService")
	private IEquipmentService equipmentService;
	
	@Autowired @Qualifier(value = "OperationTypeService")
	private IOperationTypeService operationTypeService;
	
	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	@Autowired @Qualifier(value = "RouteCreatorFacade")
	private IRouteCreatorFacade routeCreatorFacade;
	
	
	
	@GetMapping(value = "/edit-list")
	public String editListGET(Model model) {
		model.addAttribute("routes", routeService.getAll());
		return "route/edit_list";
	}
	
	@PostMapping(value = "/edit-list", params = "btnAdd")
	public String editListPOSTAdd(Model model) {
		routeCreatorFacade.reset();
		return "redirect:/route/edit-list/add-1";
	}
	
	// Сообщение об удалении добавить
	@PostMapping(value = "/edit-list", params = "btnDelete")
	public String editListPOSTDelete(RedirectAttributes redirectAttributes, @RequestParam(name = "btnDelete", defaultValue = "-1") long id) {
		routeService.deleteById(id);
		return "redirect:/route/edit-list";
	}
	
	@GetMapping(value = "/edit-list/add-1")
	public String editListAdd1GET(Model model) {
		model.addAttribute("parts", partService.getAll());
		model.addAttribute("number", routeCreatorFacade.getNumber());
		return "route/add_1";
	}
	
	/*
	 * 	TO DO
	 * - проверка введенных данных
	 */
	@PostMapping(value = "/edit-list/add-1", params = "btnNext")
	public String editListAdd1POSTNext(Model model, RedirectAttributes redirectAttributes,
			@RequestParam(name = "number") String number, 
			@RequestParam(name = "partId") String strId) {

		if(!routeCreatorFacade.setNumber(number) || !routeCreatorFacade.setPart(partService.getById(IDValidator.convertFromStringToLong(strId)).get())) {
			redirectAttributes.addFlashAttribute("msg", "Неверный ввод");
			return "redirect:/route/edit-list/add-1";
		}
		
		return "redirect:/route/edit-list/add-2";
	}
	
	
	
	
	@GetMapping(value = "/edit-list/add-2")
	public String editListAdd2GET(Model model) {
		
		model.addAttribute("blanks", blankService.getAll());
		model.addAttribute("blankRates", routeCreatorFacade.getBlanks());
		
		return "route/add_2";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnAddBlankRate")
	public String editListAdd2PostAddBlankRate(RedirectAttributes redirectAttributes,
			@RequestParam(name = "blankId") String strBlankId, 
			@RequestParam(name = "blankRate") String strBlankRate) {
		
		if(!routeCreatorFacade.addBlank(blankService.getById(IDValidator.convertFromStringToLong(strBlankId)), IDValidator.convertFromStringToFloat(strBlankRate))) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавлено успешно");
		}
		return "redirect:/route/edit-list/add-2";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnNext")
	public String editListAdd2POSTNext(Model model, RedirectAttributes redirectAttributes) {
		if(routeCreatorFacade.getBlanks().size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Добавте хотябы 1 элемент");
			return "redirect:/route/edit-list/add-2";
		}
		return "redirect:/route/edit-list/add-3";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnDeleteBlankRate")
	public String editListAdd2POSTDeleteBlankRate(Model model,
			@RequestParam(name = "btnDeleteBlankRate", defaultValue = "-1") long blankId) {
		
		routeCreatorFacade.deleteBlank(blankService.getById(blankId));
		return "redirect:/route/edit-list/add-2";
	}
	
	
	
	
	@GetMapping(value = "/edit-list/add-3")
	public String editListAdd3GET(Model model) {
		
		model.addAttribute("equipments", equipmentService.getAll());
		model.addAttribute("operationTypes", operationTypeService.getAll());
		model.addAttribute("operations", routeCreatorFacade.getOperations());
		
		return "route/add_3";
	}
	
	@PostMapping(value = "/edit-list/add-3", params = "btnAddOperation")
	public String editListAdd3POSTAddOperation(Model model, RedirectAttributes redirectAttributes,
			@RequestParam("time") float time,
			@RequestParam("index") int index,
			@RequestParam("operationTypeId") long operationTypeId, 
			@RequestParam("equipmentId") long equipmentId) {
		
		Optional<OperationTypeDTO> operation = operationTypeService.getById(operationTypeId);
		Optional<EquipmentDTO> equipment = equipmentService.getById(equipmentId);
		
		if(operation.isPresent() && equipment.isPresent()) {
			if(!routeCreatorFacade.addOperation(new OperationDTO(-1, time, index, equipment.get(), operation.get())))
				redirectAttributes.addFlashAttribute("msg", "Добавить операцию не удалось - ошибка 2");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить операцию не удалось - ошибка 1");
		}
		return "redirect:/route/edit-list/add-3";
	}
	
	@PostMapping(value = "/edit-list/add-3", params = "btnNext")
	public String editListAdd3POSTNext(Model model) {
		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	@PostMapping(value = "/edit-list/add-3", params = "btnDelete")
	public String editListAdd3POSTDelete(@RequestParam(name = "btnDelete", defaultValue = "-1") int deleteIndex, Model model) {
		routeCreatorFacade.deleteOperation(deleteIndex);
		return "redirect:/route/edit-list/add-3";
	}
	
	@GetMapping(value = "/edit-list/add-over-view-data")
	public String editListAddOverViewDataGET(Model model) {
		model.addAttribute("partDTO", routeCreatorFacade.getPart().get());
		model.addAttribute("routeNumber", routeCreatorFacade.getNumber());
		model.addAttribute("operations", routeCreatorFacade.getOperations());
		model.addAttribute("blankRates", routeCreatorFacade.getBlanks());
		return "/route/add_over_view_data";
	}
	
	@PostMapping(value = "/edit-list/add-over-view-data", params = "btnEdit")
	public String editListAddOverViewDataPOSTEdit(@RequestParam(name = "btnEdit", defaultValue = "-1") int value) {
		if(value == 1) return "redirect:/route/edit-list/add-1";
		if(value == 2) return "redirect:/route/edit-list/add-2";
		if(value == 3) return "redirect:/route/edit-list/add-3";

		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	
	@PostMapping(value = "/edit-list/add-over-view-data", params = "btnSave")
	public String editListAddOverViewDataPOSTSave(RedirectAttributes redirectAttributes) {

		RouteDTO route = new RouteDTO();
		route.setPart(routeCreatorFacade.getPart().get());
		route.setNumber(routeCreatorFacade.getNumber());
		
		List<BlankRateDTO> blankRates = routeCreatorFacade.getBlanks().entrySet().stream()
				 .map(p -> new BlankRateDTO(0, route, p.getKey(), p.getValue()))
				 .collect(Collectors.toList());
		
		if(routeService.save(route, blankRates, routeCreatorFacade.getOperations()) != null) {
			redirectAttributes.addFlashAttribute("msg", "Добавлено успешно");
			return "redirect:/route/edit-list/add-result";
		}
		
		redirectAttributes.addFlashAttribute("msg", "Ошибка при добавлении");
		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	
	@GetMapping(value = "/edit-list/add-result")
	public String editListAddResultGET(Model model) {
		return "route/add_result";
	}
	
	@PostMapping(value = "/edit-list/add-result", params = "btnAddNew")
	public String resultPOSTAddNew() {
		routeCreatorFacade.reset();
		return "redirect:/route/edit-list/add-1";
	}
	
	@PostMapping(value = "/edit-list/add-result", params = "btnShowList")
	public String resultPOSTShowList() {
		routeCreatorFacade.reset();
		return "redirect:/route/edit-list";
	}
}
