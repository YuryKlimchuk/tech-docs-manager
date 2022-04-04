package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Standart;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/specification-element/standart")
public class StandartController extends AbstractSpecificationElementController<StandartDTO, StandartEntity> {

	
	@Autowired
	public StandartController(@Qualifier(value = "StandartService") AbstractSpecificationElementService<StandartDTO, StandartEntity, Long> service) {
		super(service);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_SHOW_ITEM = "specification_element/standart/show_item";
		this.HTML_SHOW_LIST = "specification_element/standart/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/specification-element/standart/show-list/";
	}

}
