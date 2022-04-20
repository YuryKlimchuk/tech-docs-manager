package com.hydroyura.TechDocsManager.Controller.WEB.Route;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
//import com.hydroyura.TechDocsManager.Service.Raw.IBlankService;
import com.hydroyura.TechDocsManager.Service.Route.IEquipmentService;
import com.hydroyura.TechDocsManager.Service.Route.IOperationTypeService;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;


@Controller
@RequestMapping(value = "/route")
public class RouteEditController extends AbstractController {
	
	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity, Long> partService;
	
	@Autowired @Qualifier(value = "BlankService")
	private AbstractSpecificationElementService<BlankDTO, BlankEntity, Long> blankService;
	
	@Autowired @Qualifier(value = "EquipmentService")
	private IEquipmentService equipmentService;
	
	@Autowired @Qualifier(value = "OperationTypeService")
	private IOperationTypeService operationTypeService;
	
	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	private Map<BlankDTO, Float> blankRates = new LinkedHashMap<>();
	private List<OperationDTO> operations = new LinkedList<>();
	private String routeNumber;
	private PartDTO partDTO = new PartDTO();
	
	
	
	@GetMapping(value = "/edit-list/add-1")
	public String editListAdd1GET(Model model) {
		
		model.addAttribute("parts", partService.getAll());
		
		return "route/add_1";
	}
	
	/*
	 * 	TO DO
	 * - проверка введенных данных
	 */
	@PostMapping(value = "/edit-list/add-1", params = "btnNext")
	public String editListAdd1POSTNext(Model model,
			@RequestParam(name = "number") String number, 
			@RequestParam(name = "partId") String strId) {
		
		routeNumber = number;
		partDTO = partService.getById(IDValidator.convertFromStringToLong(strId)).get();
		
		return "redirect:/route/edit-list/add-2";
	}
	
	
	
	
	@GetMapping(value = "/edit-list/add-2")
	public String editListAdd2GET(Model model) {
		
		model.addAttribute("blanks", blankService.getAll());
		model.addAttribute("blankRates", blankRates);
		
		return "route/add_2";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnAddBlankRate")
	public String editListAdd2PostAddBlankRate(
			@RequestParam(name = "blankId") String strBlankId, 
			@RequestParam(name = "blankRate") String strBlankRate) {

		long blankId = IDValidator.convertFromStringToLong(strBlankId);
		float blankRate = IDValidator.convertFromStringToFloat(strBlankRate);
		
		if(blankId > 0 && blankRate > 0) {
			blankService.getById(blankId).ifPresent(p -> blankRates.put(p, blankRate));
		}

		return "redirect:/route/edit-list/add-2";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnNext")
	public String editListAdd2POSTNext(Model model) {
		return "redirect:/route/edit-list/add-3";
	}
	
	@PostMapping(value = "/edit-list/add-2", params = "btnDeleteBlankRate")
	public String editListAdd2POSTDeleteBlankRate(Model model,
			@RequestParam(name = "btnDeleteBlankRate", defaultValue = "-1") long blankId) {
		
		
		// Удалять сдесь будет искать элемент по заготовке
		
		return "redirect:/route/edit-list/add-2";
	}
	
	
	
	
	@GetMapping(value = "/edit-list/add-3")
	public String editListAdd3GET(Model model) {
		
		model.addAttribute("equipments", equipmentService.getAll());
		model.addAttribute("operationTypes", operationTypeService.getAll());
		model.addAttribute("operations", operations);
		
		return "route/add_3";
	}
	
	/*
	 * TO DO
	 * - сделать проверку пришедших данных
	 * 
	 */
	@SuppressWarnings("unused")
	@PostMapping(value = "/edit-list/add-3", params = "btnAddOperation")
	public String editListAdd3POSTAddOperation(Model model,
			@RequestParam("time") float time,
			@RequestParam("index") int index,
			@RequestParam("operationTypeId") long operationTypeId, 
			@RequestParam("equipmentId") long equipmentId
			) {
		
		System.out.println("Нажата кнопка добавить");
		
		if(true) {
			operations.add(index-1, new OperationDTO(0, time, -1, 
					equipmentService.getById(equipmentId).get(), 
					operationTypeService.getById(operationTypeId).get())
			);
		} else {
			System.out.println("invalid data");
			// тут если данные не прошли валидацию
		}

		
		return "redirect:/route/edit-list/add-3";
	}
	
	@PostMapping(value = "/edit-list/add-3", params = "btnNext")
	public String editListAdd3POSTNext(Model model) {
		
		System.out.println("Нажата кнопка Далее");
		
		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	/*
	 * 	TO DO
	 * - проверку индекса на корректность (возможность преобразовать в ключ)
	 */
	@PostMapping(value = "/edit-list/add-3", params = "btnDelete")
	public String editListAdd3POSTDelete(@RequestParam(name = "btnDelete", defaultValue = "-1") int deleteIndex, Model model) {
		
		System.out.println("Нажата кнопка Удалить");
		
		if(deleteIndex != -1) {
			operations.remove(deleteIndex);
		} else {
			// что то делаем если индекс -1
		}
		
		return "redirect:/route/edit-list/add-3";
	}
	
	
	
	
	@GetMapping(value = "/edit-list/add-over-view-data")
	public String editListAddOverViewDataGET(Model model) {
		
		System.out.println("Обзор вводимых данных");
		
		model.addAttribute("partDTO", partDTO);
		model.addAttribute("routeNumber", routeNumber);
		model.addAttribute("operations", operations);
		model.addAttribute("blankRates", blankRates);
		
		return "/route/add_over_view_data";
	}
	
	@PostMapping(value = "/edit-list/add-over-view-data", params = "btnEdit")
	public String editListAddOverViewDataPOSTEdit(@RequestParam(name = "btnEdit", defaultValue = "-1") int value) {
		
		System.out.println("Нажата кнопка редактировать, value = " + value);
		
		if(value == 1) return "redirect:/route/edit-list/add-1";
		if(value == 2) return "redirect:/route/edit-list/add-2";
		if(value == 3) return "redirect:/route/edit-list/add-3";
		
		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	/*
	 *  TO DO
	 *  - добавить Builder
	 */
	@PostMapping(value = "/edit-list/add-over-view-data", params = "btnSave")
	public String editListAddOverViewDataPOSTSave() {
		
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setNumber(routeNumber);
		routeDTO.setPart(partDTO);
		
		// тут надо сделать комплект для сохранения
		// private Map<BlankDTO, Float> blankRates = new LinkedHashMap<>();
		
		 List<BlankRateDTO> blankRateDTOs = blankRates.entrySet().stream()
				 .map(p -> new BlankRateDTO(0, routeDTO, p.getKey(), p.getValue()))
				 .collect(Collectors.toList());
		
		
		System.out.println("RouteDTO ->> " + routeDTO);
		
		if(routeService.save(routeDTO, blankRateDTOs, operations) != null) return "redirect:/route/edit-list/add-result";;
		
		return "redirect:/route/edit-list/add-over-view-data";
	}
	
	
	
	@GetMapping(value = "/edit-list/add-result")
	public String editListAddResultGET(Model model) {
		
		System.out.println("Результат добавления в БД");
		
		return "route/add_result";
	}
}
