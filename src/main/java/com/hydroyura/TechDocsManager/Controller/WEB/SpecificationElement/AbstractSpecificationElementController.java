package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

public abstract class AbstractSpecificationElementController<DTO, Entity> extends AbstractController implements ISpecificationElementController {
	
	
	protected AbstractSpecificationElementService<DTO, Entity> service;
	
	protected String HTML_SHOW_LIST;
	protected String HTML_SHOW_ITEM;
	
	protected String REDIRECT_SHOW_LIST;
	
	protected Map<String, String> searchParams = new HashMap<>();
	
	protected IFilterFactory<Entity> filterFactory;
	
	protected String KEY;
	
	
	public AbstractSpecificationElementController(AbstractSpecificationElementService<DTO, Entity> service, IFilterFactory<Entity> filterFactory) {
		this.service = service;
		this.filterFactory = filterFactory;
	}

	
	@Override
	public String showListGET(Model model) {
		
		model.addAttribute("dtos", service.getAll(filterFactory.create(searchParams).getSpecification()));
		model.addAttribute("searchParams", searchParams);
		return HTML_SHOW_LIST;
	}

	@Override
	public String showListIdGET(String strId, Model model) {
		
		Optional<DTO> dto = service.getById(IDValidator.convertFromStringToLong(strId));
		
		if(dto.isEmpty()) return REDIRECT_SHOW_LIST;
		
		model.addAttribute("dto", dto.get());
		
		//model.addAttribute("routeDTOs", service.getRoutesByPart(dto.get()));
		
		return HTML_SHOW_ITEM;
	}
	
	@Override
	public String showListPOSTSearch(Map<String, String> searchParams) {
		return REDIRECT_SHOW_LIST;
	}

	@Override
	public String showListIdPostDownload(String strId) {
		return REDIRECT_SHOW_LIST + "/" + strId + "/download-pdf";
	}

	@Override
	public ResponseEntity<byte[]> showListIdDownloadGET(String strId) {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
