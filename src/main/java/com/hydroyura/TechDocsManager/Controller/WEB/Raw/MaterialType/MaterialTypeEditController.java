package com.hydroyura.TechDocsManager.Controller.WEB.Raw.MaterialType;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

//@Controller
@RequestMapping(value = "/raw/material-type")
public class MaterialTypeEditController extends AbstractSpecificationElementEditController<MaterialTypeDTO, MaterialTypeEntity>{


	
	@Autowired
	public MaterialTypeEditController(
			@Qualifier(value = "MaterialTypeService") AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity> service,
			@Qualifier(value = "MaterialTypeDTOFactory") IDTOFactory<MaterialTypeDTO> dtoFactory) {
		
		super(service, dtoFactory);
	}
	
	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "raw/material_type/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "raw/material_type/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "raw/material_type/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "raw/material_type/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/raw/material-type/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/raw/material-type/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/raw/material-type/edit-list/accept-delete/";
	}


	@Override
	public String editListPOSTSearch(Map<String, String> searchParams) {
		// TODO Auto-generated method stub
		return super.editListPOSTSearch(searchParams);
	}
	
	
}
