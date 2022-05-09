package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Sortament;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

//@Controller
@RequestMapping(value = "/raw/sortament")
public class SortamentController extends AbstractSpecificationElementController<SortamentDTO, SortamentEntity> {

	@Autowired
	public SortamentController(
			@Qualifier(value = "SortamentService") AbstractSpecificationElementService<SortamentDTO, SortamentEntity> service) {
		super(service,null);
	}
	
	
	@PostConstruct
	private void init() {
		this.HTML_SHOW_ITEM = "raw/sortament/show_item";
		this.HTML_SHOW_LIST = "raw/sortament/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/raw/sortament/show-list/";
	}

}
