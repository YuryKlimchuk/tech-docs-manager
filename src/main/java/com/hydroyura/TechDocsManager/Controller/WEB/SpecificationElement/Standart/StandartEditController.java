package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Standart;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;


@Controller
@RequestMapping(value = "/specification-element/standart")
public class StandartEditController extends AbstractSpecificationElementEditController<StandartDTO, StandartEntity> {

	@Autowired
	public StandartEditController(@Qualifier(value = "StandartService") AbstractSpecificationElementService<StandartDTO, StandartEntity, Long> service,
							      @Qualifier(value = "StandartDTOFactory") IDTOFactory<StandartDTO> dtoFactory) {
		
		super(service, dtoFactory);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "specification_element/standart/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "specification_element/standart/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "specification_element/standart/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "specification_element/standart/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/specification-element/standart/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/specification-element/standart/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/specification-element/standart/edit-list/accept-delete/";
	}

}
