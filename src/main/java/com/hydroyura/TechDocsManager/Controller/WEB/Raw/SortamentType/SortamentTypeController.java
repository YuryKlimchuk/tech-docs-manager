package com.hydroyura.TechDocsManager.Controller.WEB.Raw.SortamentType;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/sortament-type")
public class SortamentTypeController extends AbstractSpecificationElementController<SortamentTypeDTO, SortamentTypeEntity> {

	@Autowired
	public SortamentTypeController(
			@Qualifier(value = "SortamentTypeService") AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity, Long> service) {
		super(service);
	}
	
	
	@PostConstruct
	private void init() {
		

		
		this.HTML_SHOW_ITEM = "raw/sortament_type/show_item";
		this.HTML_SHOW_LIST = "raw/sortament_type/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/raw/sortament-type/show-list/";
	}

}
