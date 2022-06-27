package com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl;

import java.util.Map;

import org.springframework.ui.Model;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.IBaseRawEditController;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

public class AbstractBaseRawEditController<DTO, Entity> extends ExtendedAbstractController<DTO, Entity> implements IBaseRawEditController<DTO> {

	private final String ATTR_NAME_ITEMS = "items";
	//private final String ATTR_NAME_ITEM = "item";
	private final String ATTR_NAME_SEARCH_PARAMS = "searchParams";
	
	private final String PATH = "/raw/";
	//private final String HTML_MAIN = "main";
	//private final String HTML_SHOW_LIST = "show_list";
	private final String HTML_EDIT_LIST = "edit_list";
	//private final String HTML_SHOW_ITEM = "show_item";

	private final String ULR_EDIT_LIST = "edit-list";
	//private final String ULR_SHOW_LIST = "show-list";
	
	protected String labelDirectory;
	protected String labelULR;
	
	public AbstractBaseRawEditController(AbstractSpecificationElementService<DTO, Entity> service,IFilterFactory<Entity> filterFactory) {
		super(service, filterFactory);
	}

	@Override
	public String editListGET(Model model) {
		model.addAttribute(ATTR_NAME_ITEMS, service.getAll(filterFactory.create(searchParams).getSpecification()));
		model.addAttribute(ATTR_NAME_SEARCH_PARAMS, searchParams);
		return PATH + labelDirectory + "/" + HTML_EDIT_LIST;
	}

	@Override
	public String editListPOSTSearch(Map<String, String> searchParams) {
		return "redirect:" + PATH + labelULR + "/" + ULR_EDIT_LIST;
	}

}
