package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Buy;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;


@Controller
@RequestMapping(value = "/specification-element/buy")
public class BuyEditController extends AbstractSpecificationElementEditController<BuyDTO, BuyEntity> {

	@Autowired
	public BuyEditController(@Qualifier(value = "BuyService") AbstractSpecificationElementService<BuyDTO, BuyEntity, Long> service,
							 @Qualifier(value = "BuyDTOFactory") IDTOFactory<BuyDTO> dtoFactory) {
		
		super(service, dtoFactory);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "specification_element/buy/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "specification_element/buy/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "specification_element/buy/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "specification_element/buy/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/specification-element/buy/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/specification-element/buy/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/specification-element/buy/edit-list/accept-delete/";
	}

}
