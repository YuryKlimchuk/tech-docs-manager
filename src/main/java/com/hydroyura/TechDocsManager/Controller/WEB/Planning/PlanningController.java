package com.hydroyura.TechDocsManager.Controller.WEB.Planning;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.TechDocsManager.Controller.WEB.AbstractController;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorBlank;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorOperation;
import com.hydroyura.TechDocsManager.Service.DOCGenerator.IDOCGenerator;
import com.hydroyura.TechDocsManager.Service.Product.IProductService;
import com.hydroyura.TechDocsManager.Service.SpecificationFacade.ISpecificationFacade;

@Controller
@RequestMapping(value = "/planning")
public class PlanningController extends AbstractController {
	
	@Autowired @Qualifier(value = "DOCGenerator")
	private IDOCGenerator docGenerator;
	
	@Autowired @Qualifier(value = "ProductService")
	private IProductService productService;
	
	@Autowired @Qualifier(value = "VisitorBlank")
	private IVisitor visitorBlank;
	
	@Autowired @Qualifier(value = "VisitorOperation")
	private IVisitor visitorOperation;
	
	@Autowired @Qualifier(value = "SpecificationFacade")
	private ISpecificationFacade specificationFacade;
	
	
	@GetMapping(value = "")
	public String showMainGET() {
		return "planning/main";
	}
	
	@PostMapping(value = "", params = "btnCreateList")
	public String showMainPOSTCreateList() {
		return "redirect:/planning/create-list";
	}
	
	@GetMapping(value = "/create-list")
	public String showCreateListGET(Model model) {
		model.addAttribute("items", productService.getAll());
		model.addAttribute("products", specificationFacade.getProducts());
		return "planning/create_list";
	}
	
	@PostMapping(value = "/create-list", params = "btnAdd")
	public String showCreateListPOSTAdd(
						@RequestParam(name = "id") long id, 
						@RequestParam(name = "count") long count, RedirectAttributes redirectAttributes) {
		
	
		Optional<ProductDTO> product = productService.getById(id);
		
		if(product.isPresent()) {
			
			String msg = specificationFacade.addProduct(product.get(), count) 
					? "Элемент добавлен успешно" : "Добавить элемент не удалось - дублирование";
			redirectAttributes.addFlashAttribute("msg", msg);
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить элемент не удалось - произошла неизместная ошибка");
		}
		
		return "redirect:/planning/create-list";
	}

	@PostMapping(value = "/create-list", params = "btnDelete")
	public String showCreateListPOSTDelete(@RequestParam(name = "btnDelete") long id, RedirectAttributes redirectAttributes) {
		
		Optional<ProductDTO> product = productService.getById(id);
		if(product.isPresent()) {
			String msg = specificationFacade.removeProduct(product.get()) 
					? "Элемент успешно удален" : "Элемент удалить не удалось - не найден";
			redirectAttributes.addFlashAttribute("msg", msg);
		} else {
			redirectAttributes.addFlashAttribute("msg", "Элемент удалить не удалось - произошла неизместная ошибка");
		}
		
		return "redirect:/planning/create-list";
	}
	
	@PostMapping(value = "/create-list", params = "btnSave")
	public String showCreateListPOSTSave() {
		return "redirect:/planning/create-list/download";
	}
	
	
	@GetMapping(value = "/create-list/download")
	public ResponseEntity<?> showCreateListDownloadGET() {
		
		Map<Long, Long> mapToSave = specificationFacade.getProducts().entrySet().stream()
				.map(p -> new AbstractMap.SimpleEntry<Long, Long>(p.getKey().getId(), p.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			byte[] byties = objectMapper.writeValueAsBytes(mapToSave);

			return 
				ResponseEntity
					.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=products_list.json")
					.contentType(MediaType.APPLICATION_JSON)
					.contentLength(byties.length)
					.body(byties);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return ResponseEntity
					.badRequest()
					.body("Ошибка при сохранении");		
		
	}
	
	
	@PostMapping(value = "/create-list", params = "btnUpload")
	public String showCreateListPOSTUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			TypeReference<HashMap<Long, Long>> typeRef = new TypeReference<HashMap<Long, Long>>() {};
			
			Map<Long, Long> mapFromFile = objectMapper.readValue(file.getBytes(), typeRef);
			specificationFacade.getProducts().clear();
			mapFromFile.forEach((key, value) -> specificationFacade.addProduct(productService.getById(key).get(), value));
			redirectAttributes.addFlashAttribute("msg", "Загрузка прошла успешно");
			return "redirect:/planning/create-list";
		} catch (IOException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("msg", "Загрузить не удалось");
		return "redirect:/planning/create-list";
	}
	
	@PostMapping(value = "/create-list", params = "btnClear")
	public String showCreateListPOSTClear() {
		specificationFacade.getProducts().clear();
		return "redirect:/planning/create-list";
	}
	
	@PostMapping(value = "/create-list", params = "btnNext")
	public String showCreateListPOSTNext() {
		return "redirect:/planning/create-list/select-assembly";
	}
	
	@GetMapping(value = "create-list/select-assembly")
	public String showCreateListSelectAssemblyGET(Model model) {
		model.addAttribute("products", specificationFacade.getProducts());
		return "planning/select_assembly";
	}
	
	//Проверка на подлинность ID
	@PostMapping(value = "create-list/select-assembly/{id}", params = "btnSelect")
	public String showCreateListSelectAssemblyPOSTSelect(
									@PathVariable(value = "id") long id,
									@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		
		if(specificationFacade.setSelectedAssembly(selectedId, id)) {
			redirectAttributes.addFlashAttribute("msg", "Сборка выбрана успешно");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Сборку выбрать не удалось");
		}

		return "redirect:/planning/create-list/select-assembly";
	}
	
	@PostMapping(value = "create-list/select-assembly", params = "btnBack")
	public String showCreateListSelectAssemblyPOSTBack() {
		return "redirect:/planning/create-list";
	}
	
	@PostMapping(value = "create-list/select-assembly", params = "btnNext")
	public String showCreateListSelectAssemblyPOSTNext(RedirectAttributes redirectAttributes) {
		
		if(specificationFacade.isAllAssembliesSelected()) {
			return "redirect:/planning/create-list/select-assembly/expanded-specification";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}		
	}
	

	@GetMapping(value = "create-list/select-assembly/expanded-specification")
	public String showCreateListSelectAssemblyExpandedSpecificationGET(Model model) {
		
		specificationFacade.generateSpecification();
		
		model.addAttribute("products", specificationFacade.getProducts());
		model.addAttribute("parts",specificationFacade.getSpecificationElement(SpecificationRowType.PART));
		model.addAttribute("standarts",specificationFacade.getSpecificationElement(SpecificationRowType.STANDART));
		model.addAttribute("buys",specificationFacade.getSpecificationElement(SpecificationRowType.BUY));
		model.addAttribute("vzks",specificationFacade.getSpecificationElement(SpecificationRowType.VZK));
		
		return "planning/expanded_specification";
	}
	
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification", params = "btnRoute")
	public String showExpandedSpecificationPOSTRoute(Model model) {
		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
	}
	
	
	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route")
	public String showSelectRouteGET(Model model, RedirectAttributes redirectAttributes) {
		
		// Проверка не пустой ли список???
		if(specificationFacade.getProducts().size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(!specificationFacade.isAllAssembliesSelected()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}

		model.addAttribute("products", specificationFacade.getProducts());
		model.addAttribute("parts", specificationFacade.getParts());
		
		return "planning/select_route";
	}
	
	// Valid ID
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/{id}")
	public String showSelectRoutePOSTSelect(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		
		if(specificationFacade.setSelectedRoute(selectedId, id)) {
			redirectAttributes.addFlashAttribute("msg", "Выбор элемента прошел успешно");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при выборе элемента");
		}

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
	}
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route", params = "btnNext")
	public String showSelectRoutePOSTNext(RedirectAttributes redirectAttributes) {
		
		if(!specificationFacade.isAllRoutesSelected()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны маршруты");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
		} 

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
	}

	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank")
	public String showSelectBlankGET(Model model, RedirectAttributes redirectAttributes) {
		// Проверка не пустой ли список???
		if(specificationFacade.getProducts().size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(!specificationFacade.isAllAssembliesSelected()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		model.addAttribute("products", specificationFacade.getProducts());
		model.addAttribute("parts", specificationFacade.getParts());

		return "planning/select_blank";
	}
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank/{id}", params = "btnSelect")
	public String showSelectBlankPOSTSelect(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		
		if(!specificationFacade.setSelectedBlankRate(selectedId, id)) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка 2");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
		}
		
		redirectAttributes.addFlashAttribute("msg", "Заготовка выбрана успешно");
		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
	}
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank", params = "btnNext")
	public String showSelectBlankPOSTNext(RedirectAttributes redirectAttributes) {

		if(!specificationFacade.isAllBlankRatesSelected()) {
			redirectAttributes.addFlashAttribute("msg", "Выбраны не все заготовки!");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
		}

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank/result";
	}

	
	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank/result")
	public String showResultGET(Model model, RedirectAttributes redirectAttributes) {
		
		// Проверка не пустой ли список???
		if(specificationFacade.getProducts().size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(!specificationFacade.isAllAssembliesSelected()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		model.addAttribute("products", specificationFacade.getProducts());
		
		// Считаем заготовки
		visitorBlank.reset();
		specificationFacade.getParts().forEach((value) -> visitorBlank.visit(value));
		((VisitorBlank) visitorBlank).getBlankRates()
			.forEach((key, value) -> System.out.println("KEY = " + key.getMaterial().getNumber() + " " + key.getSortament().getNumber() + "; VALUE = " + value));
		model.addAttribute("blanks", ((VisitorBlank) visitorBlank).getBlankRates());
		
		// расчет времени машинного
		visitorOperation.reset();
		specificationFacade.getParts().forEach(p -> visitorOperation.visit(p));
		((VisitorOperation) visitorOperation).getOperationTime()
			.forEach((key, value) -> System.out.println("KEY = " + key.getNumber() + "; VALUE = " + value));
		
		model.addAttribute("operations", ((VisitorOperation) visitorOperation).getOperationTime());
		model.addAttribute("parts",specificationFacade.getSpecificationElement(SpecificationRowType.PART));
		model.addAttribute("standarts",specificationFacade.getSpecificationElement(SpecificationRowType.STANDART));
		model.addAttribute("buys",specificationFacade.getSpecificationElement(SpecificationRowType.BUY));
		model.addAttribute("vzks",specificationFacade.getSpecificationElement(SpecificationRowType.VZK));
		
		return "planning/result";
	}
	
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank/result", params = "btnDownload")
	public ResponseEntity<?> showResultPOSTDownload() {
		
		try(ByteArrayOutputStream byteOut = new ByteArrayOutputStream();) {
			
			XWPFDocument document = docGenerator.generate(specificationFacade);
			document.write(byteOut);
			byteOut.toByteArray();
			
			return 
					ResponseEntity
						.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "test_file.docx")
						.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
						.contentLength(byteOut.toByteArray().length)
						.body(byteOut.toByteArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().body(null);	
	}	
}



