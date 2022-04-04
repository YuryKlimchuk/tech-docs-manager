package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Material;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractRawEditController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/material")
public class MaterialEditController extends AbstractRawEditController<MaterialDTO, MaterialEntity, MaterialTypeDTO, MaterialTypeEntity> {

	
	@Autowired
	public MaterialEditController(
			@Qualifier(value = "MaterialService") AbstractSpecificationElementService<MaterialDTO, MaterialEntity, Long> service,
			@Qualifier(value = "MaterialTypeService") AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity, Long> typeService,
			@Qualifier(value = "MaterialDTOFactory") IDTOFactory<MaterialDTO> dtoFactory) {
		super(service, typeService, dtoFactory);
	}
	
	@PostConstruct
	public void init() {
		this.LABEL = "material";
	}
	
	
	@Override
	public String editListAdd1POSTSave(MaterialDTO dto, long typeId, RedirectAttributes redirectAttributes) {
		
		dto.setType(typeService.getById(typeId).get());
		
		if(service.save(dto).isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Элемент успешно добавлен");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить элемент не удалось");
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list/add-1";
	}


}
