package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Material;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

//@Controller
@RequestMapping(value = "/raw/material")
public class MaterialController extends AbstractSpecificationElementController<MaterialDTO, MaterialEntity> {

	@Autowired
	public MaterialController(
			@Qualifier(value = "MaterialService") AbstractSpecificationElementService<MaterialDTO, MaterialEntity> service) {
		super(service, null);
	}
	
	
	@PostConstruct
	private void init() {
		

		
		this.HTML_SHOW_ITEM = "raw/material/show_item";
		this.HTML_SHOW_LIST = "raw/material/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/raw/material/show-list/";
	}

}
