package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Vzk;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/specification-element/vzk")
public class VzkController extends AbstractSpecificationElementController<VzkDTO, VzkEntity> {

	
	@Autowired
	public VzkController(@Qualifier(value = "VzkService") AbstractSpecificationElementService<VzkDTO, VzkEntity, Long> service) {
		super(service);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_SHOW_ITEM = "specification_element/vzk/show_item";
		this.HTML_SHOW_LIST = "specification_element/vzk/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/specification-element/vzk/show-list/";
	}

}
