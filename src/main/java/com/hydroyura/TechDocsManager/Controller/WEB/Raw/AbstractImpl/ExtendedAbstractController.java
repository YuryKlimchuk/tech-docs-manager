package com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl;

import java.util.HashMap;
import java.util.Map;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

public class ExtendedAbstractController<DTO, Entity> extends AbstractController {
	
	protected AbstractSpecificationElementService<DTO, Entity> service;
	protected IFilterFactory<Entity> filterFactory;
	
	protected Map<String, String> searchParams = new HashMap<>();
	
	public ExtendedAbstractController(AbstractSpecificationElementService<DTO, Entity> service, IFilterFactory<Entity> filterFactory) {
		this.service = service;
		this.filterFactory = filterFactory;
	}

}
