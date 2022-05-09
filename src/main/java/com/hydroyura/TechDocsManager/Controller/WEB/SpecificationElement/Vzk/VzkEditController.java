package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Vzk;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;


@Controller
@RequestMapping(value = "/specification-element/vzk")
public class VzkEditController extends AbstractSpecificationElementEditController<VzkDTO, VzkEntity> {

	@Autowired
	public VzkEditController(@Qualifier(value = "VzkService") AbstractSpecificationElementService<VzkDTO, VzkEntity> service,
							 @Qualifier(value = "VzkDTOFactory") IDTOFactory<VzkDTO> dtoFactory) {
		
		super(service, dtoFactory);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "specification_element/vzk/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "specification_element/vzk/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "specification_element/vzk/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "specification_element/vzk/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/specification-element/vzk/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/specification-element/vzk/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/specification-element/vzk/edit-list/accept-delete/";
	}

}
