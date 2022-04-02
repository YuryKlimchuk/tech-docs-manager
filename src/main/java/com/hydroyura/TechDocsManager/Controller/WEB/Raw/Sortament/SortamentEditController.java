package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Sortament;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractRawEditController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/sortament")
public class SortamentEditController extends AbstractRawEditController<SortamentDTO, SortamentEntity, SortamentTypeDTO, SortamentTypeEntity> {

	
	@Autowired
	public SortamentEditController(
			@Qualifier(value = "SortamentService") AbstractSpecificationElementService<SortamentDTO, SortamentEntity, Long> service,
			@Qualifier(value = "SortamentTypeService") AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity, Long> typeService,
			@Qualifier(value = "SortamentDTOFactory") IDTOFactory<SortamentDTO> dtoFactory) {
		super(service, typeService, dtoFactory);
	}
	
	@PostConstruct
	public void init() {
		this.LABEL = "sortament";
	}
	
	
	@Override
	public String editListAdd1POSTSave(SortamentDTO dto, long typeId, RedirectAttributes redirectAttributes) {
		
		dto.setType(typeService.getById(typeId).get());
		
		System.out.println("DTO = " + dto.getStandart());
		System.out.println("DTO = " + dto.getNumber());
		
		System.out.println("DTO = " + dto.getType().getNumber());
		
		if(service.save(dto).isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Элемент успешно добавлен");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить элемент не удалось");
		}
		
		return "redirect:/raw/" + this.LABEL + "/edit-list/add-1";
	}


}
