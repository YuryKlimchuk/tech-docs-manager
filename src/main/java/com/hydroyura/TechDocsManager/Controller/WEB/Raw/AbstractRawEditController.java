package com.hydroyura.TechDocsManager.Controller.WEB.Raw;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

public abstract class AbstractRawEditController<DTO, Entity, DTOType, EntityType>  implements IRawEditController<DTO>  {

	private IDTOFactory<DTO> dtoFactory;
	
	protected AbstractSpecificationElementService<DTO, Entity, Long> service;
	protected AbstractSpecificationElementService<DTOType, EntityType, Long> typeService;
	

	protected String LABEL;
	
	

	
	
	public AbstractRawEditController(AbstractSpecificationElementService<DTO, Entity, Long> service, AbstractSpecificationElementService<DTOType, EntityType, Long> typeService, IDTOFactory<DTO> dtoFactory) {
		this.service = service;
		this.typeService = typeService;
		this.dtoFactory = dtoFactory;
	}
	
	
	@Override
	public String editListGET(Model model) {
		model.addAttribute("items", service.getAll());
		return "raw/" + this.LABEL + "/edit_list";
	}

	@Override
	public String editListPOSTAdd() {
		return"redirect:/raw/" + this.LABEL + "/edit-list/add-1";
	}

	@Override
	public String editListPOSTDelete(String strId, RedirectAttributes redirectAttributes) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return "redirect:/raw/" + this.LABEL + "/edit-list";
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list/accept-delete/" + String.valueOf(id);
	}

	
	
	
	
	
	@Override
	public String editListPOSTEdit(String strId, RedirectAttributes redirectAttributes) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при попытке изменения");
			return "redirect:/raw/" + this.LABEL + "/edit-list";
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list" + "/" + String.valueOf(id) + "/edit-1";
	}

	@Override
	public String editListEdit1GET(String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<DTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при изменении");
			return "redirect:/raw/" + this.LABEL + "/edit-list";
		}
		
		model.addAttribute("item", item.get());
		
		return "raw/" + this.LABEL + "/edit_list_edit_1";
	}

	@Override
	public String editListAcceptDeleteGET(String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<DTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return "redirect:/raw/" + this.LABEL + "/edit-list";
		}
		
		model.addAttribute("item", item.get());
		
		return "raw/" + this.LABEL + "/edit_list_accept_delete";
	}

	@Override
	public String editListAcceptDeletePOSTDelete(String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<DTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
		} else {
			service.deleteById(id);
			redirectAttributes.addFlashAttribute("msg", "Удаление прошло успешно");
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list";
	}

	@Override
	public String editListAcceptDeletePOSTCancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Отмена удаления");
		return "redirect:/raw/" + this.LABEL + "/edit-list";
	}

	@Override
	public String editListEdit1POSTCancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Отмена редактирования");
		return "redirect:/raw/" + this.LABEL + "/edit-list";
	}

	@Override
	public String editListEdit1POSTSave(DTO item, String strId, RedirectAttributes redirectAttributes) {

		if(service.save(item).isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Ошибка при изменении");
		} else {
			
			redirectAttributes.addFlashAttribute("msg", "Деталь успешно изменена");
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list";
	}

	@Override
	public String editListAdd1GET(Model model) {
		model.addAttribute("dto", dtoFactory.create()); 
		model.addAttribute("types", typeService.getAll()); 
		return "raw/" + this.LABEL + "/edit_list_add_1";
	}



}
