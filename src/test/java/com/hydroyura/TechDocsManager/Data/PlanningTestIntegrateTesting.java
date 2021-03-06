package com.hydroyura.TechDocsManager.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Service.Composite.ICompositeUtilities;
import com.hydroyura.TechDocsManager.Service.Composite.Create.ICompositeStructureCreator;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.IVisitor;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorBlank;
import com.hydroyura.TechDocsManager.Service.Composite.Visitor.VisitorOperation;
import com.hydroyura.TechDocsManager.Service.Product.IProductService;
import com.hydroyura.TechDocsManager.Service.Route.IEquipmentService;
import com.hydroyura.TechDocsManager.Service.Route.IOperationTypeService;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PlanningTestIntegrateTesting {
	

	
	@Autowired @Qualifier(value = "VisitorBlank")
	private IVisitor visitorBlank;
	
	@Autowired @Qualifier(value = "VisitorOperation")
	private IVisitor visitorOperation;
	
	
	
	@Autowired @Qualifier(value = "ProductService")
	private IProductService productService;
	
	@Autowired @Qualifier(value = "EquipmentService")
	private IEquipmentService equipmentService;
	
	@Autowired @Qualifier(value = "OperationTypeService")
	private IOperationTypeService operationTypeService;

	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity> partService;
	
	@Autowired @Qualifier(value = "AssemblyService")
	private AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity> assemblyService;
	
	@Autowired @Qualifier(value = "AssemblyPartRateService")
	private AbstractAssemblyRateService<PartEntity, PartDTO> partRateService;
	
	@Autowired @Qualifier(value = "AssemblyAssemblyRateService")
	private AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> assemblyRateService;
	
	

	@Autowired @Qualifier(value = "MaterialTypeService")
	private AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity> materialTypeService;

	@Autowired @Qualifier(value = "SortamentTypeService")
	private AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity> sortamentTypeService;
	
	@Autowired @Qualifier(value = "SortamentService")
	private AbstractSpecificationElementService<SortamentDTO, SortamentEntity> sortamentService;
	
	@Autowired @Qualifier(value = "MaterialService")
	private AbstractSpecificationElementService<MaterialDTO, MaterialEntity> materialService;
	
	@Autowired @Qualifier(value = "BlankService")
	private AbstractSpecificationElementService<BlankDTO, BlankEntity> blankService;
	
	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	
	
	@Autowired @Qualifier(value = "CompositeCreatorV1")
	private ICompositeStructureCreator compositeCreator;

	
	@Test
	void autowiredTest() {
		assertNotNull(partService);
		assertNotNull(equipmentService);
		assertNotNull(blankService);
		assertNotNull(operationTypeService);
		assertNotNull(productService);
		assertNotNull(assemblyService);
		assertNotNull(partRateService);
		assertNotNull(assemblyRateService);
		assertNotNull(materialTypeService);
		assertNotNull(sortamentTypeService);
		assertNotNull(sortamentService);
		assertNotNull(materialService);
		assertNotNull(routeService);
		assertNotNull(compositeCreator);
	}
	
	@Test
	void test1() throws Exception {
		
		PartDTO part1 = partService.save(new PartDTO(0, "??????????_????????????_01", "??????_????????????_01", "????????????????_????????????")).get();
		PartDTO part2 = partService.save(new PartDTO(0, "??????????_????????????_02", "??????_????????????_02", "????????????????_????????????")).get();
		PartDTO part3 = partService.save(new PartDTO(0, "??????????_????????????_03", "??????_????????????_03", "????????????????_????????????")).get();
		PartDTO part4 = partService.save(new PartDTO(0, "??????????_????????????_04", "??????_????????????_04", "????????????????_????????????")).get();
		PartDTO part5 = partService.save(new PartDTO(0, "??????????_????????????_05", "??????_????????????_05", "????????????????_????????????")).get();
		PartDTO part6 = partService.save(new PartDTO(0, "??????????_????????????_06", "??????_????????????_06", "????????????????_????????????")).get();
	
		
		AssemblyDTO assembly1 = assemblyService.save(new AssemblyDTO("??????????_????????????_01", "??????_????????????_01", "????????????????_????????????")).get();
		assertEquals("??????????_????????????_01", assembly1.getNumber());
		AssemblyDTO assembly2 = assemblyService.save(new AssemblyDTO("??????????_????????????_02", "??????_????????????_02", "????????????????_????????????")).get();
		assertEquals("??????????_????????????_02", assembly2.getNumber());
		AssemblyDTO assembly3 = assemblyService.save(new AssemblyDTO("??????????_????????????_03", "??????_????????????_03", "????????????????_????????????")).get();
		assertEquals("??????????_????????????_03", assembly3.getNumber());
		AssemblyDTO assembly4 = assemblyService.save(new AssemblyDTO("??????????_????????????_04", "??????_????????????_04", "????????????????_????????????")).get();
		assertEquals("??????????_????????????_04", assembly4.getNumber());
		
		// --- assembly1
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly1, part1, 2));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly1, part2, 4));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly1, part3, 6));
		assemblyRateService.save(new BaseAssemblyRateDTO<AssemblyDTO>(assembly1, assembly4, 2));
		
		// --- assembly2
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly2, part1, 3));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly2, part2, 2));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly2, part4, 8));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly2, part6, 2));
		assemblyRateService.save(new BaseAssemblyRateDTO<AssemblyDTO>(assembly2, assembly4, 1));
		
		// --- assembly3
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly3, part5, 1));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly3, part4, 1));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly3, part3, 3));
		
		// --- assembly4
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly4, part4, 4));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly4, part5, 1));
		partRateService.save(new BaseAssemblyRateDTO<PartDTO>(assembly4, part3, 3));
		
		// -- equipment00...04
		EquipmentDTO stanok0 = equipmentService.save(new EquipmentDTO(0, "????????????_00")).get(); // ????????
		EquipmentDTO stanok1 = equipmentService.save(new EquipmentDTO(0, "????????????_01")).get(); // ????????
		EquipmentDTO stanok2 = equipmentService.save(new EquipmentDTO(0, "????????????_02")).get(); // ??????
		EquipmentDTO stanok3 = equipmentService.save(new EquipmentDTO(0, "????????????_03")).get(); // ??????
		EquipmentDTO stanok4 = equipmentService.save(new EquipmentDTO(0, "????????????_04")).get(); // ??????????????????
		
		// -- operationType
		OperationTypeDTO grinding = operationTypeService.save(new OperationTypeDTO(0, "TEST_????????????????????????")).get();
		OperationTypeDTO lathing = operationTypeService.save(new OperationTypeDTO(0, "TEST_????????????????")).get();
		OperationTypeDTO milling = operationTypeService.save(new OperationTypeDTO(0, "TEST_??????????????????")).get();
		
		// --mat types
		MaterialTypeDTO steel     = materialTypeService.save(new MaterialTypeDTO(0, "TEST_??????????")).get();
		MaterialTypeDTO castSteel = materialTypeService.save(new MaterialTypeDTO(0, "TEST_??????????")).get();
		
		// --sort type
		SortamentTypeDTO circle = sortamentTypeService.save(new SortamentTypeDTO(0, "TEST_????????")).get();
		SortamentTypeDTO hex = sortamentTypeService.save(new SortamentTypeDTO(0, "TEST_????????????????????????")).get();
		SortamentTypeDTO shtyka = sortamentTypeService.save(new SortamentTypeDTO(0, "TEST_?????????????? ??????????????????")).get();
		
		
		// --sort
		SortamentDTO circle12 = sortamentService.save(new SortamentDTO(0, circle, "???????? 12", "?????????????? ???????? ???? ????????")).get();
		SortamentDTO hex27 = sortamentService.save(new SortamentDTO(0, hex, "???????????????????????? 27", "???????? ???? ????????????????????????")).get();
		SortamentDTO hex32 = sortamentService.save(new SortamentDTO(0, hex, "???????????????????????? 32", "???????? ???? ????????????????????????")).get();
		SortamentDTO otlivkaRG = sortamentService.save(new SortamentDTO(0, shtyka, "?????????????? RG", "??????????????")).get();
		
		// mat
		MaterialDTO steel40X = materialService.save(new MaterialDTO(0, steel, "TEST_40??", "???????? ?????? ?????????? 40??")).get();
		MaterialDTO steel35 = materialService.save(new MaterialDTO(0, steel, "TEST_35", "???????? ?????? ?????????? 35")).get();
		MaterialDTO castSteelSCH25 = materialService.save(new MaterialDTO(0, castSteel, "TEST_????25", "???????? ?????? ???????????? ????????????")).get();
		
		
		// blank
		BlankDTO circle12Steel35 = blankService.save(new BlankDTO(0, steel35, circle12)).get();
		BlankDTO hex27Steel40X = blankService.save(new BlankDTO(0, steel40X, hex27)).get();
		BlankDTO hex32Steel40X = blankService.save(new BlankDTO(0, steel40X, hex32)).get();
		BlankDTO otlivka1 = blankService.save(new BlankDTO(0, castSteelSCH25, otlivkaRG)).get();
		
		
		ProductDTO product1 = new ProductDTO();
		product1.setNumber("??????????????_01");
		product1.setType("????????????????????????????");
		product1.setAssemblies(Arrays.asList(assembly1));
		product1 = productService.save(product1).get();
		product1.setSelectedAssembly(assembly1);
		assertEquals("??????????????_01", productService.getById(product1.getId()).get().getNumber());
		
		ProductDTO product2 = new ProductDTO();
		product2.setNumber("??????????????_02");
		product2.setType("????????????????????????????");
		product2.setAssemblies(Arrays.asList(assembly2));
		product2 = productService.save(product2).get();
		product2.setSelectedAssembly(assembly2);
		assertEquals("??????????????_02", productService.getById(product2.getId()).get().getNumber());
		
		ProductDTO product3 = new ProductDTO();
		product3.setNumber("??????????????_03");
		product3.setType("????????????????????????????");
		product3.setAssemblies(Arrays.asList(assembly3));
		product3 = productService.save(product3).get();
		product3.setSelectedAssembly(assembly3);
		assertEquals("??????????????_03", productService.getById(product3.getId()).get().getNumber());
		
		Map<ProductDTO, Long> products = new LinkedHashMap<ProductDTO, Long>();
		products.put(product1, 2L);
		products.put(product2, 1L);
		products.put(product3, 3L);
		assertEquals(3, products.size());
		
		
		/* 
		 * 	???????????? ????????????????????????:
		 * 	????????????
		 * 	1. pr.1 - 2
		 * 	2. pr.2 - 1
		 * 	3. pr.3 - 3
		 *  
		 *  ??????????????????
		 *  pr.1(2) ::: p1(4) - p2(8) - p3(24) - p4(16) - p5(4)
		 *  pr.2(1) ::: p1(3) - p2(2) - p3(3)  - p4(12) - p5(1) - p6(2)  
		 *  pr.3(3) :::                 p3(9)  - p4(3)  - p5(3)
		 *  
		 *  ??????.    ::: p1(7) - p2(10) - p3(36)  - p4(31) - p5(8) - p6(2)  
		 *  
		 *  
		 *  ?????????????? ???? ????????????????????:
		 *  ????????12????35  = 46.1
		 *  Hex27????40?? = 109.7
		 *  Hex32????40?? = 9.2
		 *  ?????????????? RG = 2
		 *  
		 */
		List<Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>>> listSpecification = new ArrayList<>();
		products.forEach((key, value) -> listSpecification.add(compositeCreator.createChildren(key.getSelectedAssembly(), value).getValue()));
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> specification = new HashMap<>();
		listSpecification.forEach(value -> ICompositeUtilities.mergeMap1(specification, value));
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> compressSpecification = ICompositeUtilities.compressMap(specification);
		assertEquals(6, compressSpecification.get(SpecificationRowType.PART).size());
		
		
	
		Map.Entry<ISpecificationRow, List<Long>> valuePart1 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part1.getNumber() + " " + part1.getName() + " (" + part1.getStatus() + ")")))
			.findFirst().get();
		assertEquals(7, valuePart1.getValue().stream().mapToLong(p -> p).sum());
		
		Map.Entry<ISpecificationRow, List<Long>> valuePart2 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part2.getNumber() + " " + part2.getName() + " (" + part2.getStatus() + ")")))
			.findFirst().get();
		assertEquals(10, valuePart2.getValue().stream().mapToLong(p -> p).sum());
		
		Map.Entry<ISpecificationRow, List<Long>> valuePart3 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part3.getNumber() + " " + part3.getName() + " (" + part3.getStatus() + ")")))
			.findFirst().get();
		assertEquals(36, valuePart3.getValue().stream().mapToLong(p -> p).sum());
		
		Map.Entry<ISpecificationRow, List<Long>> valuePart4 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part4.getNumber() + " " + part4.getName() + " (" + part4.getStatus() + ")")))
			.findFirst().get();
		assertEquals(31, valuePart4.getValue().stream().mapToLong(p -> p).sum());

		Map.Entry<ISpecificationRow, List<Long>> valuePart5 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part5.getNumber() + " " + part5.getName() + " (" + part5.getStatus() + ")")))
			.findFirst().get();
		assertEquals(8, valuePart5.getValue().stream().mapToLong(p -> p).sum());
		
		Map.Entry<ISpecificationRow, List<Long>> valuePart6 = compressSpecification.get(SpecificationRowType.PART).entrySet().stream()
			.filter(p -> (p.getKey().getLabel().equals(part6.getNumber() + " " + part6.getName() + " (" + part6.getStatus() + ")")))
			.findFirst().get();
		assertEquals(2, valuePart6.getValue().stream().mapToLong(p -> p).sum());
		// ----- ?????????? ?????????????? ??????????????
		
		
		// ---------------- ???????????????? ---------------------------------
		Map<String, RouteDTO> routiesMap = new HashMap<>();
		RouteDTO routePart1 = new RouteDTO(0, "ROUTE_PART_1", part1);
		routePart1 = routeService.save(
				routePart1, 
				Arrays.asList(
					new BlankRateDTO(0, routePart1, circle12Steel35, 2.3f)
				), 
				Arrays.asList(
					new OperationDTO(0, 0.3f, 0, stanok0, grinding),
					new OperationDTO(0, 0.23f, 1, stanok1, grinding),
					new OperationDTO(0, 0.33f, 2, stanok2, lathing),
					new OperationDTO(0, 0.63f, 3, stanok4, milling)
				)
		);
		routiesMap.put(part1.getNumber() + " " + part1.getName() + " (" + part1.getStatus() + ")", routePart1);
		
		RouteDTO routePart2 = new RouteDTO(0, "ROUTE_PART_2", part2);
		routePart2 = routeService.save(
				routePart2, 
				Arrays.asList(
					new BlankRateDTO(0, routePart2, circle12Steel35, 3f)
				), 
				Arrays.asList(
					new OperationDTO(0, 0.2f, 0, stanok1, grinding),
					new OperationDTO(0, 0.3f, 1, stanok3, lathing),
					new OperationDTO(0, 0.6f, 2, stanok4, milling)
				)
		);
		routiesMap.put(part2.getNumber() + " " + part2.getName() + " (" + part2.getStatus() + ")", routePart2);
		
		RouteDTO routePart3 = new RouteDTO(0, "ROUTE_PART_3", part3);
		routePart3 = routeService.save(
				routePart3, 
				Arrays.asList(
					new BlankRateDTO(0, routePart3, hex27Steel40X, 2.1f)
				), 
				Arrays.asList(
					new OperationDTO(0, 1.3f, 0, stanok0, grinding),
					new OperationDTO(0, 2.23f, 1, stanok1, grinding)
				)
		);
		routiesMap.put(part3.getNumber() + " " + part3.getName() + " (" + part3.getStatus() + ")", routePart3);
		
		RouteDTO routePart4 = new RouteDTO(0, "ROUTE_PART_4", part4);
		routePart4 = routeService.save(
				routePart4, 
				Arrays.asList(
					new BlankRateDTO(0, routePart4, hex27Steel40X, 1.1f)
				), 
				Arrays.asList(
					new OperationDTO(0, 0.13f, 0, stanok1, grinding),
					new OperationDTO(0, 0.43f, 1, stanok2, lathing),
					new OperationDTO(0, 0.3f, 3, stanok4, milling)
				)
		);
		routiesMap.put(part4.getNumber() + " " + part4.getName() + " (" + part4.getStatus() + ")", routePart4);
		
		RouteDTO routePart5 = new RouteDTO(0, "ROUTE_PART_5", part5);
		routePart5 = routeService.save(
				routePart5, 
				Arrays.asList(
					new BlankRateDTO(0, routePart5, hex32Steel40X, 1.15f)
				), 
				Arrays.asList(
					new OperationDTO(0, 0.13f, 0, stanok0, grinding),
					new OperationDTO(0, 0.40f, 1, stanok1, grinding),
					new OperationDTO(0, 0.73f, 2, stanok2, lathing)
				)
		);
		routiesMap.put(part5.getNumber() + " " + part5.getName() + " (" + part5.getStatus() + ")", routePart5);
		
		RouteDTO routePart6 = new RouteDTO(0, "ROUTE_PART_6", part6);
		routePart6 = routeService.save(
				routePart6, 
				Arrays.asList(
					new BlankRateDTO(0, routePart6, otlivka1, 1f)
				), 
				Arrays.asList(
					new OperationDTO(0, 0.45f, 0, stanok2, grinding),
					new OperationDTO(0, 0.27f, 1, stanok4, milling)
				)
		);
		routiesMap.put(part6.getNumber() + " " + part6.getName() + " (" + part2.getStatus() + ")", routePart6);
		// -----------------------------------------------------
		
		Map<ISpecificationRow, List<Long>> partsMap = compressSpecification.get(SpecificationRowType.PART);
		partsMap.forEach(new BiConsumer<ISpecificationRow, List<Long>>() {

			@Override
			public void accept(ISpecificationRow t, List<Long> u) {
				t.setCount(u.get(0));
			}
			
		});
		
		// ???????????? ????????????????
		Set<ISpecificationRow> parts = partsMap.keySet();
		parts.forEach(p -> ((SpecificationRowPart) p).setRoutes(Arrays.asList(routiesMap.get(p.getLabel()))));
		
		
		// ???????????? ??????????????????
		parts.forEach(p -> visitorBlank.visit(p));
		((VisitorBlank) visitorBlank).getBlankRates()
			.forEach((key, value) -> System.out.println("KEY =" + key.getMaterial().getNumber() + " " + key.getSortament().getNumber() + "; VALUE = " + value));
		
		
		// ???????????? ?????????????? ??????????????????
		parts.forEach(p -> visitorOperation.visit(p));
		((VisitorOperation) visitorOperation).getOperationTime()
			.forEach((key, value) -> System.out.println("KEY = " + key.getNumber() + "; VALUE = " + value));
		
		
		
	}
	

	
	
	
}
