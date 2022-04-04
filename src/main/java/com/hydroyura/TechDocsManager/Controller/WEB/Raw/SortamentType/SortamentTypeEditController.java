package com.hydroyura.TechDocsManager.Controller.WEB.Raw.SortamentType;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/sortament-type")
public class SortamentTypeEditController extends AbstractSpecificationElementEditController<SortamentTypeDTO, SortamentTypeEntity>{


	
	@Autowired
	public SortamentTypeEditController(
			@Qualifier(value = "SortamentTypeService") AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity, Long> service,
			@Qualifier(value = "SortamentTypeDTOFactory") IDTOFactory<SortamentTypeDTO> dtoFactory) {
		
		super(service, dtoFactory);
	}
	
	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "raw/sortament_type/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "raw/sortament_type/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "raw/sortament_type/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "raw/sortament_type/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/raw/sortament-type/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/raw/sortament-type/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/raw/sortament-type/edit-list/accept-delete/";
	}
	
}
