package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

public abstract class AbstractSpecificationElementEditController<DTO, Entity> extends AbstractController  implements ISpecificationElementEditController<DTO> {

	private IDTOFactory<DTO> dtoFactory;
	
	protected AbstractSpecificationElementService<DTO, Entity> service;
	
	protected String HTML_EDIT_LIST;
	protected String HTML_EDIT_LIST_ADD_1;
	protected String HTML_EDIT_LIST_ACCEPT_DELETE;
	protected String HTML_EDIT_LIST_EDIT_1;
	
	protected String REDIRECT_EDIT_LIST_ADD_1;
	protected String REDIRECT_EDIT_LIST;
	protected String REDIRECT_EDIT_LIST_ACCEPT_DELETE;
	
	protected Map<String, String> searchParams = new HashMap<>();
	protected IFilterFactory<Entity> filterFactory;

	
	
	public AbstractSpecificationElementEditController(AbstractSpecificationElementService<DTO, Entity> service, IDTOFactory<DTO> dtoFactory, IFilterFactory<Entity> filterFactory) {
		this.service = service;
		this.dtoFactory = dtoFactory;
		this.filterFactory = filterFactory;
	}
	
	// Удалить
	public AbstractSpecificationElementEditController(AbstractSpecificationElementService<DTO, Entity> service, IDTOFactory<DTO> dtoFactory) {
		this.service = service;
		this.dtoFactory = dtoFactory;
		this.filterFactory = null;
	}
	
	@Override
	public String editListGET(Model model) {
		model.addAttribute("searchParams", searchParams);
		
		if(filterFactory == null) {
			model.addAttribute("items", service.getAll());
		} else {
			model.addAttribute("items", service.getAll(filterFactory.create(searchParams).getSpecification()));
		}
		
		return this.HTML_EDIT_LIST;
	}

	
	
	@Override
	public String editListPOSTSearch(Map<String, String> searchParams) {
		return REDIRECT_EDIT_LIST;
	}


	@Override
	public String editListPOSTAdd() {
		return REDIRECT_EDIT_LIST_ADD_1;
	}

	@Override
	public String editListPOSTDelete(String strId, RedirectAttributes redirectAttributes) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return REDIRECT_EDIT_LIST;
		}
		
		return REDIRECT_EDIT_LIST_ACCEPT_DELETE + String.valueOf(id);
	}

	@Override
	public String editListPOSTEdit(String strId, RedirectAttributes redirectAttributes) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		if(id == -1) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при попытке изменения");
			return REDIRECT_EDIT_LIST;
		}
		
		return REDIRECT_EDIT_LIST + "/" + String.valueOf(id) + "/edit-1";
	}

	@Override
	public String editListEdit1GET(String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<DTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при изменении");
			return REDIRECT_EDIT_LIST;
		}
		
		model.addAttribute("item", item.get());
		
		return HTML_EDIT_LIST_EDIT_1;
	}

	@Override
	public String editListAcceptDeleteGET(String strId, RedirectAttributes redirectAttributes, Model model) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		
		Optional<DTO> item = service.getById(id);
		
		if(item.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при удалении");
			return REDIRECT_EDIT_LIST;
		}
		
		model.addAttribute("item", item.get());
		
		return HTML_EDIT_LIST_ACCEPT_DELETE;
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
		
		return REDIRECT_EDIT_LIST;
	}

	@Override
	public String editListAcceptDeletePOSTCancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Отмена удаления");
		return REDIRECT_EDIT_LIST;
	}

	@Override
	public String editListEdit1POSTCancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Отмена редактирования");
		return REDIRECT_EDIT_LIST;
	}

	@Override
	public String editListEdit1POSTSave(DTO item, String strId, RedirectAttributes redirectAttributes) {

		if(service.save(item).isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Ошибка при изменении");
		} else {
			
			redirectAttributes.addFlashAttribute("msg", "Деталь успешно изменена");
		}
		
		return REDIRECT_EDIT_LIST;
	}

	@Override
	public String editListAdd1GET(Model model) {
		model.addAttribute("dto", dtoFactory.create()); 
		return HTML_EDIT_LIST_ADD_1;
	}

	
	/*
	 * 		TO DO:
	 * 1. Сохранение PDF
	 * 2. Валидация
	 */
	@Override
	public String editListAdd1PostSave(DTO dto, MultipartFile pdf, RedirectAttributes redirectAttributes) {
		
		/* Подумать как добавить PDF сохранение
		if(pdf.getSize() > 0) {
			try {
				dto.setPdf(pdf.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/ 
		
		if(service.save(dto).isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Элемент успешно добавлен");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить элемент не удалось");
		}
		
		return REDIRECT_EDIT_LIST_ADD_1;
	}

}
