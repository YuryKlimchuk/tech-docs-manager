package com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.Assembly;

import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hydroyura.TechDocsManager.Controller.WEB.SpecificationElement.AbstractSpecificationElementEditController;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.DTOFactory.IDTOFactory;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.Factory.IFilterFactory;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;
import com.hydroyura.TechDocsManager.Utils.IDValidator;

@Controller
@RequestMapping(value = "/specification-element/assembly")
public class AssemblyEditController extends AbstractSpecificationElementEditController<AssemblyDTO, AssemblyEntity> {

	@Autowired @Qualifier(value = "AssemblyAssemblyRateService")
	private AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> assemblyAssemblyRateService;
	
	@Autowired @Qualifier(value = "AssemblyPartRateService")
	private AbstractAssemblyRateService<PartEntity, PartDTO> assemblyPartRateService;
	
	@Autowired @Qualifier(value = "AssemblyStandartRateService")
	private AbstractAssemblyRateService<StandartEntity, StandartDTO> assemblyStandartRateService;
	
	@Autowired @Qualifier(value = "AssemblyVzkRateService")
	private AbstractAssemblyRateService<VzkEntity, VzkDTO> assemblyVzkRateService;
	
	@Autowired @Qualifier(value = "AssemblyBuyRateService")
	private AbstractAssemblyRateService<BuyEntity, BuyDTO> assemblyBuyRateService;
	
	
	
	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity> partService;
	
	@Autowired @Qualifier(value = "StandartService")
	private AbstractSpecificationElementService<StandartDTO, StandartEntity> standartService;
	
	@Autowired @Qualifier(value = "VzkService")
	private AbstractSpecificationElementService<VzkDTO, VzkEntity> vzkService;
	
	@Autowired @Qualifier(value = "BuyService")
	private AbstractSpecificationElementService<BuyDTO, BuyEntity> buyService;
	
	
	
	@Autowired
	public AssemblyEditController(@Qualifier(value = "AssemblyService") AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity> service, 
								  @Qualifier(value = "AssemblyDTOFactory") IDTOFactory<AssemblyDTO> dtoFactory,
								  @Qualifier(value = "AssemblyFilterFactory") IFilterFactory<AssemblyEntity> filterFactory) {
		
		super(service, dtoFactory, filterFactory);
	}

	
	@PostConstruct
	private void init() {
		
		this.HTML_EDIT_LIST = "specification_element/assembly/edit_list";
		this.HTML_EDIT_LIST_ADD_1 = "specification_element/assembly/edit_list_add_1";
		this.HTML_EDIT_LIST_ACCEPT_DELETE = "specification_element/assembly/edit_list_accept_delete";
		this.HTML_EDIT_LIST_EDIT_1 = "specification_element/assembly/edit_list_edit_1";
		
		this.REDIRECT_EDIT_LIST_ADD_1 = "redirect:/specification-element/assembly/edit-list/add-1";
		this.REDIRECT_EDIT_LIST = "redirect:/specification-element/assembly/edit-list";
		this.REDIRECT_EDIT_LIST_ACCEPT_DELETE = "redirect:/specification-element/assembly/edit-list/accept-delete/";
	}
	
	
	
	@Override
	public String editListPOSTSearch(Map<String, String> searchParamsReturned) {
		String name = searchParamsReturned.containsKey("name") ? searchParamsReturned.get("name") : "";
		String number = searchParamsReturned.containsKey("number") ? searchParamsReturned.get("number") : "";
		
		this.searchParams.put("name", name);
		this.searchParams.put("number", number);
		
		return super.editListPOSTSearch(searchParams);
	}


	@PostMapping(value = "/edit-list/{strId}/edit-1", params = "btnEditSpec")
	public String editListEdit1POSTeditSpec(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model) {
		// Какуюто верификацию добавить
		return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";
	}
	
	
	@GetMapping(value = "/edit-list/{strId}/edit-1/edit-specification")
	public String editListEdit1EditSpecGET(@PathVariable("strId") String strId, RedirectAttributes redirectAttributes, Model model) {
	
		Optional<AssemblyDTO> assemblyRoot = service.getById(IDValidator.convertFromStringToLong(strId));
		
		// Добавить валидацию
		model.addAttribute("assembly", service.getById(IDValidator.convertFromStringToLong(strId)).get());
		
		
		model.addAttribute("assembliesForAdding", service.getAll(null));
		model.addAttribute("partsForAdding", partService.getAll(null));
		model.addAttribute("standartsForAdding", standartService.getAll(null));
		model.addAttribute("vzksForAdding", vzkService.getAll(null));
		model.addAttribute("buysForAdding", buyService.getAll(null));
		
		// assemblies
		model.addAttribute("assemblies", assemblyAssemblyRateService.getByAssembly(assemblyRoot.get()));
		model.addAttribute("parts", assemblyPartRateService.getByAssembly(assemblyRoot.get()));
		model.addAttribute("standarts", assemblyStandartRateService.getByAssembly(assemblyRoot.get()));
		model.addAttribute("vzks", assemblyVzkRateService.getByAssembly(assemblyRoot.get()));
		model.addAttribute("buys", assemblyBuyRateService.getByAssembly(assemblyRoot.get()));
		
		return "/specification_element/assembly/edit_list_edit_spec";
	}

	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnAddAssembly")
	public String editListEdit1EditSpecPOSTAddAssembly(
							@PathVariable(name = "strId") String strId, 
							@RequestParam(name = "assemblyAddingId") long assemblyAddingId,	
							@RequestParam(name = "assemblyAddingCount", defaultValue = "-1") long count,
							RedirectAttributes redirectAttributes, Model model) {
		return addItemToSpecification(count, strId, assemblyAddingId, redirectAttributes, service, assemblyAssemblyRateService);
	}

	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnDeleteAssembly")
	public String editListEdit1EditSpecPOSTDeleteAssembly(
							@PathVariable(name = "strId") String strId,
							@RequestParam(name = "btnDeleteAssembly", defaultValue = "-1") long assemblyDeletingId,
							RedirectAttributes redirectAttributes) {
		return deleteItemFromSpecification(assemblyAssemblyRateService, strId, assemblyDeletingId, redirectAttributes);
	}
	
	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnAddPart")
	public String editListEdit1EditSpecPOSTAddPart(
							@PathVariable(name = "strId") String strId, 
							@RequestParam(name = "partAddingId") long itemAddingId,	
							@RequestParam(name = "partAddingCount", defaultValue = "-1") long count,
							RedirectAttributes redirectAttributes, Model model) {
		return addItemToSpecification(count, strId, itemAddingId, redirectAttributes, partService, assemblyPartRateService);
	}
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnDeletePart")
	public String editListEdit1EditSpecPOSTDeletePart(
							@PathVariable(name = "strId") String strId,
							@RequestParam(name = "btnDeletePart", defaultValue = "-1") long partDeletingId,
							RedirectAttributes redirectAttributes) {
		return deleteItemFromSpecification(assemblyPartRateService, strId, partDeletingId, redirectAttributes);
	}
	
	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnAddStandart")
	public String editListEdit1EditSpecPOSTAddStandart(
							@PathVariable(name = "strId") String strId, 
							@RequestParam(name = "standartAddingId") long itemAddingId,	
							@RequestParam(name = "standartAddingCount", defaultValue = "-1") long count,
							RedirectAttributes redirectAttributes, Model model) {
		return addItemToSpecification(count, strId, itemAddingId, redirectAttributes, standartService, assemblyStandartRateService);
	}
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnDeleteStandart")
	public String editListEdit1EditSpecPOSTDeleteStandart(
							@PathVariable(name = "strId") String strId,
							@RequestParam(name = "btnDeleteStandart", defaultValue = "-1") long itemDeletingId,
							RedirectAttributes redirectAttributes) {
		return deleteItemFromSpecification(assemblyStandartRateService, strId, itemDeletingId, redirectAttributes);
	}
	
	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnAddVzk")
	public String editListEdit1EditSpecPOSTAddVzk(
							@PathVariable(name = "strId") String strId, 
							@RequestParam(name = "vzkAddingId") long itemAddingId,	
							@RequestParam(name = "vzkAddingCount", defaultValue = "-1") long count,
							RedirectAttributes redirectAttributes, Model model) {
		return addItemToSpecification(count, strId, itemAddingId, redirectAttributes, vzkService, assemblyVzkRateService);
	}
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnDeleteVzk")
	public String editListEdit1EditSpecPOSTDeleteVzk(
							@PathVariable(name = "strId") String strId,
							@RequestParam(name = "btnDeleteVzk", defaultValue = "-1") long itemDeletingId,
							RedirectAttributes redirectAttributes) {
		return deleteItemFromSpecification(assemblyVzkRateService, strId, itemDeletingId, redirectAttributes);
	}
	
	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnAddBuy")
	public String editListEdit1EditSpecPOSTAddBuy(
							@PathVariable(name = "strId") String strId, 
							@RequestParam(name = "buyAddingId") long itemAddingId,	
							@RequestParam(name = "buyAddingCount", defaultValue = "-1") long count,
							RedirectAttributes redirectAttributes, Model model) {
		return addItemToSpecification(count, strId, itemAddingId, redirectAttributes, buyService, assemblyBuyRateService);
	}
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnDeleteBuy")
	public String editListEdit1EditSpecPOSTDeleteBuy(
							@PathVariable(name = "strId") String strId,
							@RequestParam(name = "btnDeleteBuy", defaultValue = "-1") long itemDeletingId,
							RedirectAttributes redirectAttributes) {
		return deleteItemFromSpecification(assemblyBuyRateService, strId, itemDeletingId, redirectAttributes);
	}

	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification", params = "btnCopy")
	public String editListEdit1EditSpecPOSTbtnCopy(@PathVariable(name = "strId") String strId) {
		return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification/copy-from";
	}
	
	@GetMapping(value = "/edit-list/{strId}/edit-1/edit-specification/copy-from")
	public String editListEdit1EditSpecCopyGET(@PathVariable(name = "strId") String strId, Model model) {
		
		model.addAttribute("assemblies", service.getAll());
		
		return "specification_element/assembly/edit_list_spec_copy_from";
	}
	
	
	@PostMapping(value = "/edit-list/{strId}/edit-1/edit-specification/copy-from")
	@Transactional // надо подумать как ее убрать отсюда
	public String editListEdit1EditSpecCopyPOSTCopy(@PathVariable(name = "strId") String strId,
											        @RequestParam(name = "copyAssemblyId") String strIdAdding,
											        RedirectAttributes redirectAttributes) {
		
		long id = IDValidator.convertFromStringToLong(strId);
		long idAdding = IDValidator.convertFromStringToLong(strIdAdding);
		
		Optional<AssemblyDTO> root = service.getById(id);
		Optional<AssemblyDTO> forAdding = service.getById(idAdding);
		
		System.out.println("ID = " + id + "; ID_ADDING = " + idAdding);
		
		if(root.isEmpty() || forAdding.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при копировании сборки");
		} else {
			
		    long start = System.currentTimeMillis();
		        
			assemblyAssemblyRateService.deleteByAssembly(root.get());
			assemblyPartRateService.deleteByAssembly(root.get());
			assemblyStandartRateService.deleteByAssembly(root.get());
			assemblyBuyRateService.deleteByAssembly(root.get());
			assemblyVzkRateService.deleteByAssembly(root.get());
			
			assemblyAssemblyRateService.copy(forAdding.get(), root.get());
			assemblyPartRateService.copy(forAdding.get(), root.get());
			assemblyStandartRateService.copy(forAdding.get(), root.get());
			assemblyBuyRateService.copy(forAdding.get(), root.get());
			assemblyVzkRateService.copy(forAdding.get(), root.get());
			
			long end = System.currentTimeMillis();
			
			System.out.println("TIME: "+ (end-start)); 
			

		}
		
		return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";
	}

	
	private<E, D> String addItemToSpecification(long count, String strId, long addingItemId, RedirectAttributes redirectAttributes,
												AbstractSpecificationElementService<D, E> service1,
												AbstractAssemblyRateService<E, D> service2) {
		
		Optional<AssemblyDTO> assemblyRoot = service.getById(IDValidator.convertFromStringToLong(strId));
		if(assemblyRoot.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка, сборка не найдена в базе");
			return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";
		} 
		
		if(count <= 0) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка, неверное количество");
			return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";
		}
		
		
		Optional<D> itemForAdding = service1.getById(addingItemId);
		if(itemForAdding.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Добавить не удалось");
		} else {
			Optional<BaseAssemblyRateDTO<D>> rateDTO = service2.save(new BaseAssemblyRateDTO<D>(assemblyRoot.get(), itemForAdding.get(), count));
			if(rateDTO.isEmpty()) {
				redirectAttributes.addFlashAttribute("msg", "Добавить не удалось, дублирование");
			}
		}
		
		return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";

	}
	
	private<E, D> String deleteItemFromSpecification(AbstractAssemblyRateService<E, D> service, 
													 String strId, long deletingId,
													 RedirectAttributes redirectAttributes) {
		
		if(deletingId <= 0) {
			redirectAttributes.addFlashAttribute("msg", "При удалении возникла ошибка");
		} else {
			service.deleteById(deletingId);
			redirectAttributes.addFlashAttribute("msg", "Удаление прошло успешно");
		}
		
		return "redirect:/specification-element/assembly/edit-list/" + strId + "/edit-1/edit-specification";
	}
	
}