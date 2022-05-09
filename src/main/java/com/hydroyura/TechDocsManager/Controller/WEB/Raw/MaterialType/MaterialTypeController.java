package com.hydroyura.TechDocsManager.Controller.WEB.Raw.MaterialType;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

//@Controller
@RequestMapping(value = "/raw/material-type")
public class MaterialTypeController extends AbstractSpecificationElementController<MaterialTypeDTO, MaterialTypeEntity> {

	@Autowired
	public MaterialTypeController(
			@Qualifier(value = "MaterialTypeService") AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity> service) {
		super(service,null);
	}
	
	
	@PostConstruct
	private void init() {
		

		
		this.HTML_SHOW_ITEM = "raw/material_type/show_item";
		this.HTML_SHOW_LIST = "raw/material_type/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/raw/material-type/show-list/";
	}

}
