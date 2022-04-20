package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

public abstract class AbstractSpecificationElementController<DTO, Entity> extends AbstractController implements ISpecificationElementController {
	
	
	protected AbstractSpecificationElementService<DTO, Entity, Long> service;
	
	protected String HTML_SHOW_LIST;
	protected String HTML_SHOW_ITEM;
	
	protected String REDIRECT_SHOW_LIST;
	
	
	
	
	protected String KEY;
	
	
	public AbstractSpecificationElementController(AbstractSpecificationElementService<DTO, Entity, Long> service) {
		this.service = service;
	}

	
	@Override
	public String showListGET(Model model) {
		model.addAttribute("dtos", service.getAll());
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
	public String showListIdPostDownload(String strId) {
		return REDIRECT_SHOW_LIST + "/" + strId + "/download-pdf";
	}

	@Override
	public ResponseEntity<byte[]> showListIdDownloadGET(String strId) {
		// TODO Auto-generated method stub
		return null;
	}

}
