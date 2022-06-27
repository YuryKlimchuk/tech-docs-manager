package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Material;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl.AbstractBaseRawShowController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/material/show-list")
public class MaterialShowController extends AbstractBaseRawShowController<MaterialDTO, MaterialEntity> {

	@Autowired
	public MaterialShowController(
			@Qualifier(value = "MaterialService") AbstractSpecificationElementService<MaterialDTO, MaterialEntity> service, 
			@Qualifier(value = "MaterialFilterFactory") IFilterFactory<MaterialEntity> filterFactory) {
		
		super(service, filterFactory);
		this.labelDirectory = "material";
		this.labelULR = "material";
		
		this.searchParams.put("standart", "");
		this.searchParams.put("number", "");
	}

	@Override
	public String showListPOSTSearch(Map<String, String> searchParamsReturned) {
		
		String standart = searchParamsReturned.containsKey("standart") ? searchParamsReturned.get("standart") : "";
		String number = searchParamsReturned.containsKey("number") ? searchParamsReturned.get("number") : "";
		
		this.searchParams.put("standart", standart);
		this.searchParams.put("number", number);
		
		return super.showListPOSTSearch(searchParams);
	}

}
