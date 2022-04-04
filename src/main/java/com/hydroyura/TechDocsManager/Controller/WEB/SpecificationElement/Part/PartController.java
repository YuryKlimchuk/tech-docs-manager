package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Part;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/specification-element/part")
public class PartController extends AbstractSpecificationElementController<PartDTO, PartEntity> {

	
	@Autowired
	public PartController(@Qualifier(value = "PartService") AbstractSpecificationElementService<PartDTO, PartEntity, Long> service) {
		super(service);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_SHOW_ITEM = "specification_element/part/show_item";
		this.HTML_SHOW_LIST = "specification_element/part/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/specification-element/part/show-list/";
	}

}
