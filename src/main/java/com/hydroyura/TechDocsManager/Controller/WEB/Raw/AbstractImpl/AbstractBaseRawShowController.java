package com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.IBaseRawShowController;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

public class AbstractBaseRawShowController<DTO, Entity> extends ExtendedAbstractController<DTO, Entity> implements IBaseRawShowController<DTO> {

	
	private final String ATTR_NAME_ITEMS = "items";
	private final String ATTR_NAME_ITEM = "item";
	private final String ATTR_NAME_SEARCH_PARAMS = "searchParams";
	
	private final String PATH = "/raw/";
	//private final String HTML_MAIN = "main";
	private final String HTML_SHOW_LIST = "show_list";
	private final String HTML_SHOW_ITEM = "show_item";

	//private final String ULR_EDIT_LIST = "edit-list";
	private final String ULR_SHOW_LIST = "show-list";
	
	protected String labelDirectory;
	protected String labelULR;
	
	
	public AbstractBaseRawShowController(AbstractSpecificationElementService<DTO, Entity> service, IFilterFactory<Entity> filterFactory) {
		super(service, filterFactory);
	}
	
	
	@Override
	public String showListGET(Model model) {
		model.addAttribute(ATTR_NAME_ITEMS, service.getAll(filterFactory.create(searchParams).getSpecification()));
		model.addAttribute(ATTR_NAME_SEARCH_PARAMS, searchParams);
		return PATH + labelDirectory + "/" + HTML_SHOW_LIST;
	}

	@Override
	public String showListPOSTSearch(Map<String, String> searchParams) {
		return "redirect:" + PATH + labelULR + "/" + ULR_SHOW_LIST;
	}

	@Override
	public String showItemGET(String strId, RedirectAttributes redirectAttributes, Model model) {
		Optional<DTO> dto = service.getById(IDValidator.convertFromStringToLong(strId));
		
		if(dto.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка: неверный ID");
			return "redirect:" + PATH + labelULR + "/" + ULR_SHOW_LIST;
		}
		
		model.addAttribute(ATTR_NAME_ITEM, dto.get());
		return PATH + labelDirectory + "/" + HTML_SHOW_ITEM;
	}

}
