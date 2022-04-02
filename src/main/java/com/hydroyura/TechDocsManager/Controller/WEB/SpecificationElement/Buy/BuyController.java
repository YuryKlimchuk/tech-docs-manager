package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Buy;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/specification-element/buy")
public class BuyController extends AbstractSpecificationElementController<BuyDTO, BuyEntity> {

	
	@Autowired
	public BuyController(@Qualifier(value = "BuyService") AbstractSpecificationElementService<BuyDTO, BuyEntity, Long> service) {
		super(service);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_SHOW_ITEM = "specification_element/buy/show_item";
		this.HTML_SHOW_LIST = "specification_element/buy/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/specification-element/buy/show-list/";
	}

}
