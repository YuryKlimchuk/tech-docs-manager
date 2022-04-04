package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Assembly;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Service.Composite.ICompositeUtilities;
import com.hydroyura.TechDocsManager.Service.Composite.Create.ICompositeStructureCreator;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorSpecification;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "/specification-element/assembly")
public class AssemblyController extends AbstractSpecificationElementController<AssemblyDTO, AssemblyEntity> {

	@Autowired @Qualifier(value = "AssemblyPartRateService")
	private AbstractAssemblyRateService<PartEntity, PartDTO> assemblyPartRateService; 
	
	@Autowired @Qualifier(value = "AssemblyStandartRateService")
	private AbstractAssemblyRateService<StandartEntity, StandartDTO> assemblyStandartRateService; 
	
	@Autowired @Qualifier(value = "AssemblyVzkRateService")
	private AbstractAssemblyRateService<VzkEntity, VzkDTO> assemblyVzkRateService; 
	
	@Autowired @Qualifier(value = "AssemblyAssemblyRateService")
	private AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> assemblyAssemblyRateService; 
	
	@Autowired @Qualifier(value = "CompositeCreatorV1")
	private ICompositeStructureCreator compositeCreator;
	
	
	@Autowired @Qualifier(value = "VisitorSpecification")
	private VisitorSpecification visitorSpecification;
	
	
	@Autowired
	public AssemblyController(@Qualifier(value = "AssemblyService") AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity, Long> service) {
		super(service);
	}
	
	@PostConstruct
	private void init() {
		
		this.HTML_SHOW_ITEM = "specification_element/assembly/show_item";
		this.HTML_SHOW_LIST = "specification_element/assembly/show_list";
		
		this.REDIRECT_SHOW_LIST = "redirect:/specification-element/assembly/show-list/";
	}

	@Override
	public String showListIdGET(String strId, Model model) {

		Optional<AssemblyDTO> dto = service.getById(IDValidator.convertFromStringToLong(strId));
		
		if(dto.isEmpty()) return "redirect:/specification-element/assembly/show-list";
		
		visitorSpecification.clear();
		compositeCreator.createChildren(dto.get(), -1L).getChildRows().forEach(visitorSpecification::visit);
		
		model.addAttribute("dto", dto.get());
		model.addAttribute("specification", visitorSpecification);
		
		return "specification_element/assembly/show_item";
	}
	

	
	@PostMapping(value = "/show-list/{id}", params = "btnExpandedAssembly")
	public String showListIdPostExpandedAssembly(@PathVariable(name = "id") String strId) {
		if(!(IDValidator.convertFromStringToLong(strId) > 0)) return "redirect:/specification-element/assembly/show-list";
		return "redirect:/specification-element/assembly/show-list/" + strId + "/expanded-assembly"; 
	}
	
	
	
	@GetMapping(value = "/show-list/{id}/expanded-assembly")
	public String showListIdExpandedAssemblyGET(@PathVariable(name = "id") String strId, Model model) {
		Optional<AssemblyDTO> dto = service.getById(IDValidator.convertFromStringToLong(strId));
		if(dto.isEmpty()) return "redirect:/specification-element/assembly/show-list";
		
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> assemblyMap = ICompositeUtilities.compressMap(compositeCreator.createChildren(dto.get(), -1L).getValue());
		
		model.addAttribute("dto", dto.get());

		if(assemblyMap.get(SpecificationRowType.PART) != null) {
			model.addAttribute("parts", 
					assemblyMap.get(SpecificationRowType.PART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(assemblyMap.get(SpecificationRowType.STANDART) != null) {
			model.addAttribute("standarts", 
					assemblyMap.get(SpecificationRowType.STANDART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(assemblyMap.get(SpecificationRowType.BUY) != null) {
			model.addAttribute("buys", 
					assemblyMap.get(SpecificationRowType.BUY).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(assemblyMap.get(SpecificationRowType.VZK) != null) {
			model.addAttribute("vzks", 
					assemblyMap.get(SpecificationRowType.VZK).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		return "specification_element/assembly/show_item_expanded_assembly";
	}
	
	
	
	@PostMapping(value = "/show-list/{id}/expanded-assembly", params = "btnBack")
	public String showListIdExpandedAssemblyPOSTBack(@PathVariable(name = "id") String strId) {
		if(!(IDValidator.convertFromStringToLong(strId) > 0)) return "redirect:/specification-element/assembly/show-list";
		return "redirect:/specification-element/assembly/show-list/" + strId;
	}

}


