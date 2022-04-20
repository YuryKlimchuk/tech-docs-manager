package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Blank;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@Controller
@RequestMapping(value = "/raw/blank")
public class BlankEditController extends AbstractController {
	
	@Autowired @Qualifier(value = "BlankService")
	private AbstractSpecificationElementService<BlankDTO, BlankEntity, Long> blankService;
	
	@Autowired @Qualifier(value = "SortamentService")
	private AbstractSpecificationElementService<SortamentDTO, SortamentEntity, Long> sortamentService;
	
	@Autowired @Qualifier(value = "MaterialService")
	private AbstractSpecificationElementService<MaterialDTO, MaterialEntity, Long> materialService;
	
	// может и не нужно????
	@Autowired @Qualifier(value = "BlankDTOFactory")
	private IDTOFactory<BlankDTO> blankDTOFactory;
	
	private String LABEL;
	
	@PostConstruct
	private void init() {
		this.LABEL = "blank";
	}
	
	@GetMapping(value = "/edit-list")
	public String showEditListGET(Model model) {
		
		model.addAttribute("items", blankService.getAll());
		
		return "raw/" + this.LABEL + "/edit_list";
	}
	
	@PostMapping(value = "/edit-list", params = "btnAdd")
	public String showEditListPOSTAdd() {
		return "redirect:/raw/" + this.LABEL + "/edit-list/add-1";
	}
	
	@GetMapping(value = "/edit-list/add-1")
	public String showEditListAdd1Get(Model model) {
		
		model.addAttribute("materials", materialService.getAll());
		model.addAttribute("sortaments", sortamentService.getAll());
		
		return "raw/" + this.LABEL + "/edit_list_add_1";
	}
	
	
	@PostMapping(value = "/edit-list/add-1", params = "btnSave")
	public String showEditListAddPost(RedirectAttributes redirectAttributes,
			@RequestParam(name = "materialId", defaultValue = "-1") long materialId, 
			@RequestParam(name = "sortamentId", defaultValue = "-1") long sortamentId) {
		
		
		if(materialId == -1 || sortamentId == -1) {
			redirectAttributes.addFlashAttribute("msg", "Добавить заготовку не удалось");
		} else {
			BlankDTO blank = blankDTOFactory.create();
			blank.setMaterial(materialService.getById(materialId).get());
			blank.setSortament(sortamentService.getById(sortamentId).get());
			
			blankService.save(blank);
			
			redirectAttributes.addFlashAttribute("msg", "Заготовка добавлена успешно");
		}
		
		System.out.println("Нажата кнопка btnSave, materialId = " + materialId + "; sortamentId = " + sortamentId);
		
		
		return "redirect:/raw/" + this.LABEL + "/edit-list/add-1";
		
	}
	
	
	
	
	
}
