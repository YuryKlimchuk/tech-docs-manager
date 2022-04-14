package com.hydroyura.TechDocsManager.Controller.WEB.Planning;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Service.Composite.ICompositeUtilities;
import com.hydroyura.TechDocsManager.Service.Composite.Create.ICompositeStructureCreator;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorBlank;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorOperation;
import com.hydroyura.TechDocsManager.Service.Product.IProductService;

@Controller
@RequestMapping(value = "/planning")
public class PlanningController {
	
	@Autowired @Qualifier(value = "ProductService")
	private IProductService productService;
	
	@Autowired @Qualifier(value = "CompositeCreatorV1")
	private ICompositeStructureCreator compositeCreator;
	
	@Autowired @Qualifier(value = "VisitorBlank")
	private IVisitor visitorBlank;
	
	@Autowired @Qualifier(value = "VisitorOperation")
	private IVisitor visitorOperation;
	
	
	private Map<ProductDTO, Long> products = new LinkedHashMap<ProductDTO, Long>();
	
	private Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> compressSpecification;
	
	
	private Map<SpecificationRowPart, List<Long>> parts;
	
	
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
		model.addAttribute("products", products);
		return "planning/create_list";
	}
	
	@PostMapping(value = "/create-list", params = "btnAdd")
	public String showCreateListPOSTAdd(
						@RequestParam(name = "id") long id, 
						@RequestParam(name = "count") long count, RedirectAttributes redirectAttributes) {
		
	
		Optional<ProductDTO> product = productService.getById(id);
		
		if(product.isPresent()) {
			// если такой элемент есть, надо об этом сообщить
			products.put(product.get(), Long.valueOf(count));
			redirectAttributes.addFlashAttribute("msg", "Элемент добавлен успешно");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Добавить элемент не удалось");
		}
		
		return "redirect:/planning/create-list";
	}

	
	@PostMapping(value = "/create-list", params = "btnDelete")
	public String showCreateListPOSTDelete(@RequestParam(name = "btnDelete") long id, RedirectAttributes redirectAttributes) {
		
		Optional<ProductDTO> product = productService.getById(id);
		if(product.isPresent()) {
			products.remove(product.get());
			redirectAttributes.addFlashAttribute("msg", "Элемент успешно удален");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Элемент удалить не удалось");
		}
		
		return "redirect:/planning/create-list";
	}
	
	@PostMapping(value = "/create-list", params = "btnSave")
	public String showCreateListPOSTSave() {
		return "redirect:/planning/create-list/download";
	}
	
	@GetMapping(value = "/create-list/download")
	public ResponseEntity<?> showCreateListDownloadGET() {
		
		Map<Long, Long> mapToSave = products.entrySet().stream()
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
			products.clear();
			mapFromFile.forEach((key, value) -> products.put(productService.getById(key).get(), value));
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
		products.clear();
		return "redirect:/planning/create-list";
	}
	
	
	@PostMapping(value = "/create-list", params = "btnNext")
	public String showCreateListPOSTNext() {
		return "redirect:/planning/create-list/select-assembly";
	}
	
	
	@GetMapping(value = "create-list/select-assembly")
	public String showCreateListSelectAssemblyGET(Model model) {
		model.addAttribute("products", products);
		return "planning/select_assembly";
	}
	
	//Проверка на подлинность ID
	@PostMapping(value = "create-list/select-assembly/{id}", params = "btnSelect")
	public String showCreateListSelectAssemblyPOSTSelect(
									@PathVariable(value = "id") long id,
									@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		
		Optional<Map.Entry<ProductDTO, Long>> product = products.entrySet().stream().filter(t -> t.getKey().getId() == id).findFirst();
		
		if(product.isPresent()) {
			Optional<AssemblyDTO> assembly = product.get().getKey().getAssemblies().stream().filter(t -> t.getId() == selectedId).findFirst();
			if(assembly.isPresent()) {
				product.get().getKey().setSelectedAssembly(assembly.get());
				redirectAttributes.addFlashAttribute("msg", "Сборка выбрана успешно");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Сборку выбрать не удалось");
			}
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
		
		// Проверяем все ли сборки заполнены ???
		List<ProductDTO> emptyProducts = products.entrySet().stream()
				.filter(t -> t.getKey().getSelectedAssembly() == null)
				.map(t -> t.getKey())
				.collect(Collectors.toList());
		
		if(!emptyProducts.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		return "redirect:/planning/create-list/select-assembly/expanded-specification";
	}
	
	
	
	@GetMapping(value = "create-list/select-assembly/expanded-specification")
	public String showCreateListSelectAssemblyExpandedSpecificationGET(Model model) {
		model.addAttribute("products", products);
		
		List<Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>>> listSpecification = new ArrayList<>();
		products.forEach((key, value) -> listSpecification.add(compositeCreator.createChildren(key.getSelectedAssembly(), value).getValue()));
		

		
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> specification = new HashMap<>();
		listSpecification.forEach(value -> ICompositeUtilities.mergeMap1(specification, value));
		
		compressSpecification = ICompositeUtilities.compressMap(specification);
		
		
		
		if(compressSpecification.get(SpecificationRowType.PART) != null) {
			model.addAttribute("parts", 
					compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.STANDART) != null) {
			model.addAttribute("standarts", 
					compressSpecification.get(SpecificationRowType.STANDART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.BUY) != null) {
			model.addAttribute("buys", 
					compressSpecification.get(SpecificationRowType.BUY).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.VZK) != null) {
			model.addAttribute("vzks", 
					compressSpecification.get(SpecificationRowType.VZK).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		
		return "planning/expanded_specification";
	}
	
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification", params = "btnRoute")
	public String showExpandedSpecificationPOSTRoute(Model model) {
		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
	}
	
	
	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route")
	public String showSelectRouteGET(Model model, RedirectAttributes redirectAttributes) {
		
		// Проверка не пустой ли список???
		if(products.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(products.keySet().stream().filter(pr -> pr.getSelectedAssembly() == null).findFirst().isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		// Проверем чтобы была спецификация
		if(compressSpecification == null || compressSpecification.size() == 0) {
			return "redirect:/planning/create-list/select-assembly/expanded-specification";
		}
		
		model.addAttribute("products", products);
		
		parts = new HashMap<>();
		compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new))
				.forEach((key, value) -> parts.put((SpecificationRowPart) key, value));
		
		model.addAttribute("parts", parts);
		
		return "planning/select_route";
	}
	
	
	// Valid ID
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/{id}")
	public String showSelectRoutePOSTSelect(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		

		Optional<Map.Entry<SpecificationRowPart, List<Long>>> part = parts.entrySet().stream()
					.filter(p -> p.getKey().getId() == id)
					.findFirst();
		
		
		if(part.isPresent()) {
			Optional<RouteDTO> route = part.get().getKey().getRoutes().stream().filter(p -> p.getId() == selectedId).findFirst();
			
			if(route.isPresent()) {
				part.get().getKey().setSelectedRoute(route.get());
				redirectAttributes.addFlashAttribute("msg", "Произошла ошибка при выборе элемента");
			}
			
			redirectAttributes.addFlashAttribute("msg", "Выбор элемента прошел успешно");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка");
		}
		
		
		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
	}
	
	

	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route", params = "btnNext")
	public String showSelectRoutePOSTNext(RedirectAttributes redirectAttributes) {
	
		// Проверка все ли маршруты выбраны
		List<SpecificationRowPart> emptyParts = parts.entrySet().stream()
														.filter(p -> p.getKey().getSelectedRoute() == null)
														.map(p -> p.getKey())
														.collect(Collectors.toList());
		
		if(!emptyParts.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны маршруты");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route";
		} 

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
	}

	
	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank")
	public String showSelectBlankGET(Model model, RedirectAttributes redirectAttributes) {
		// Проверка не пустой ли список???
		if(products.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(products.keySet().stream().filter(pr -> pr.getSelectedAssembly() == null).findFirst().isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		// Проверем чтобы была спецификация
		if(compressSpecification == null || compressSpecification.size() == 0) {
			return "redirect:/planning/create-list/select-assembly/expanded-specification";
		}
		
		model.addAttribute("products", products);

		compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new))
			.forEach((key, value) -> parts.put((SpecificationRowPart) key, value));

		model.addAttribute("parts", parts);

		return "planning/select_blank";
	}
	
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank/{id}", params = "btnSelect")
	public String showSelectBlankPOSTSelect(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "selectedId") long selectedId, RedirectAttributes redirectAttributes) {
		
		// Ищем ту делаль с которой работем
		Optional<SpecificationRowPart> part = parts.entrySet().stream()
				.map(p -> p.getKey())
				.filter(p -> p.getId() == id)
				.findFirst();
		if(part.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка 1");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
		}
		
		
		
		// Ищем норму расхода что нам надо
		Optional<BlankRateDTO> blankRate = part.get().getSelectedRoute().getRates().stream()
																						.filter(p -> p.getId() == selectedId)
																						.findFirst();
		if(blankRate.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Произошла ошибка 2");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
		}
		
		part.get().getSelectedRoute().setSelectedRate(blankRate.get());
 		

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
	}
	
	
	@PostMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank", params = "btnNext")
	public String showSelectBlankPOSTNext(RedirectAttributes redirectAttributes) {

		// Проверка чтобы все заготовки были выбраны
		List<BlankRateDTO> emptyBlankRates = parts.entrySet().stream()
				.map(p -> p.getKey().getSelectedRoute().getSelectedRate())
				.filter(p -> p == null)
				.collect(Collectors.toList());
		
		if(emptyBlankRates.size() != 0) {
			redirectAttributes.addFlashAttribute("msg", "Выбраны не все заготовки!");
			return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank";
		}

		return "redirect:/planning/create-list/select-assembly/expanded-specification/select-route/select-blank/result";
	}
	
	
	@GetMapping(value = "create-list/select-assembly/expanded-specification/select-route/select-blank/result")
	public String showResultGET(Model model, RedirectAttributes redirectAttributes) {
		
		// Проверка не пустой ли список???
		if(products.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "Список пуст, добавте хотябы один элемент");
			return "redirect:/planning/create-list";
		}
		
		// Проверка все ли сборки заполнены ???
		if(products.keySet().stream().filter(pr -> pr.getSelectedAssembly() == null).findFirst().isPresent()) {
			redirectAttributes.addFlashAttribute("msg", "Не для всех изделий выбраны сборки");
			return "redirect:/planning/create-list/select-assembly";
		}
		
		// Проверем чтобы была спецификация
		if(compressSpecification == null || compressSpecification.size() == 0) {
			return "redirect:/planning/create-list/select-assembly/expanded-specification";
		}
		
		model.addAttribute("products", products);
		
		// Заносим количество из массива в сами строчки спецухи
		List<SpecificationRowPart> updateParts = parts.entrySet().stream()
				.map(new Function<Map.Entry<SpecificationRowPart, List<Long>>, SpecificationRowPart>() {

					@Override
					public SpecificationRowPart apply(Entry<SpecificationRowPart, List<Long>> t) {
						SpecificationRowPart part = t.getKey();
						part.setCount(t.getValue().stream().mapToLong(p -> p).sum());
						return part;
					}
					
				})
				.collect(Collectors.toList());
		
		// Считаем заготовки
		visitorBlank.reset();
		updateParts.forEach((value) -> visitorBlank.visit(value));
		((VisitorBlank) visitorBlank).getBlankRates()
			.forEach((key, value) -> System.out.println("KEY = " + key.getMaterial().getNumber() + " " + key.getSortament().getNumber() + "; VALUE = " + value));
		model.addAttribute("blanks", ((VisitorBlank) visitorBlank).getBlankRates());
		
		// расчет времени машинного
		visitorOperation.reset();
		updateParts.forEach(p -> visitorOperation.visit(p));
		((VisitorOperation) visitorOperation).getOperationTime()
			.forEach((key, value) -> System.out.println("KEY = " + key.getNumber() + "; VALUE = " + value));
		model.addAttribute("operations", ((VisitorOperation) visitorOperation).getOperationTime());
		
		
		
		if(compressSpecification.get(SpecificationRowType.PART) != null) {
			model.addAttribute("parts", 
					compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.STANDART) != null) {
			model.addAttribute("standarts", 
					compressSpecification.get(SpecificationRowType.STANDART).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.BUY) != null) {
			model.addAttribute("buys", 
					compressSpecification.get(SpecificationRowType.BUY).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		if(compressSpecification.get(SpecificationRowType.VZK) != null) {
			model.addAttribute("vzks", 
					compressSpecification.get(SpecificationRowType.VZK).entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new)));
		}
		
		return "planning/result";
	}
	
	
	
	
	
}




