package com.hydroyura.TechDocsManager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.MaterialTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.SortamentTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.MaterialTypeEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.SortamentTypeEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.Product.ICustomerService;
import com.hydroyura.TechDocsManager.Service.Route.IEquipmentService;
import com.hydroyura.TechDocsManager.Service.Route.IOperationTypeService;
import com.hydroyura.TechDocsManager.Service.Route.Impl.EquipmentService;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AbstractSpecificationElementService;

@Component
public class UnitBD {

	@Autowired @Qualifier(value = "CustomerService")
	private ICustomerService customerService;
	
	@Autowired @Qualifier(value = "AssemblyService")
	private AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity, Long> assemblyService;
	
	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity, Long> partService;
	
	@Autowired @Qualifier(value = "StandartService")
	private AbstractSpecificationElementService<StandartDTO, StandartEntity, Long> standartService;
	
	@Autowired @Qualifier(value = "VzkService")
	private AbstractSpecificationElementService<VzkDTO, VzkEntity, Long> vzkService;
	
	@Autowired @Qualifier(value = "BuyService")
	private AbstractSpecificationElementService<BuyDTO, BuyEntity, Long> buyService;
	

	@Autowired @Qualifier(value = "MaterialTypeService")
	private AbstractSpecificationElementService<MaterialTypeDTO, MaterialTypeEntity, Long> materialTypeService;
	
	@Autowired @Qualifier(value = "SortamentTypeService")
	private AbstractSpecificationElementService<SortamentTypeDTO, SortamentTypeEntity, Long> sortamentTypeService;
	
	
	@Autowired @Qualifier(value = "OperationTypeService")
	private IOperationTypeService operationTypeService;
	
	@Autowired @Qualifier(value = "EquipmentService")
	private IEquipmentService equipmentService;
	
	
	@PostConstruct
	private void addOperationType() {
		
		//operationTypeService.save(new OperationTypeDTO(0, "Круглошлифовальная"));
		//operationTypeService.save(new OperationTypeDTO(0, "Токарная"));
		//operationTypeService.save(new OperationTypeDTO(0, "Фрезерная"));
		//operationTypeService.save(new OperationTypeDTO(0, "Плоскошлифовальная"));
		
		//equipmentService.save(new EquipmentDTO(0, "Станок#000"));
		//equipmentService.save(new EquipmentDTO(0, "Станок#001"));
		//equipmentService.save(new EquipmentDTO(0, "Станок#002"));
		//equipmentService.save(new EquipmentDTO(0, "Станок#003"));
		//equipmentService.save(new EquipmentDTO(0, "Станок#004"));
		
		
	}
	
	
	//@PostConstruct
	private void testingFeatures() {
		Map<String, String> map = new HashMap<>();
		System.out.println(" ДО добавление элементов = " + map.entrySet() == null);
		map.put("LJ", "dsd");
		map.put("LJ323", "dsd2");
		map.put("L2J", "dsd3");
		System.out.println(" ДО добавление элементов = " + map.entrySet() == null);
	}
	
	@PostConstruct
	private void initMaterialType() {
		//materialTypeService.save(new MaterialTypeDTO(0, "Сталь"));
		//materialTypeService.save(new MaterialTypeDTO(0, "Чугун"));
		//materialTypeService.save(new MaterialTypeDTO(0, "Алюминий"));
		
		//sortamentTypeService.save(new SortamentTypeDTO(0, "Круг"));
		//sortamentTypeService.save(new SortamentTypeDTO(0, "Шестигранник"));
		//sortamentTypeService.save(new SortamentTypeDTO(0, "Прямоугольник"));
		//sortamentTypeService.save(new SortamentTypeDTO(0, "Труба круглая"));
		//sortamentTypeService.save(new SortamentTypeDTO(0, "Штучная заготовка"));
	}
	
	//@PostConstruct
	private void init1() {
		//customerService.save(new CustomerDTO("МТЗ", "Беларусь"));
		//customerService.save(new CustomerDTO("Амкодор-УКХ", "Беларусь"));
		//customerService.save(new CustomerDTO("Амкодор-КЭЗ", "Беларусь"));
		//customerService.save(new CustomerDTO("МАЗ", "Беларусь"));
		//customerService.save(new CustomerDTO("БЕЛАЗ", "Беларусь"));
		
		//customerService.save(new CustomerDTO("БТЗ", "РФ"));
		//customerService.save(new CustomerDTO("ПТЗ", "РФ"));
		
	}
	
	//@PostConstruct
	private void init2() {
		assemblyService.save(new AssemblyDTO("BPG000-00000 СБ", "Блок питания", "Серия"));
		
		assemblyService.save(new AssemblyDTO("BPG100-00000 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG100-00000-01 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG100-00000-02 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG100-00000-03 СБ", "Блок питания", "Серия"));
		
		assemblyService.save(new AssemblyDTO("BPG201-00000 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG201-00000-01 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG201-00000-02 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG201-00000-03 СБ", "Блок питания", "Серия"));
		
		assemblyService.save(new AssemblyDTO("BPG201-10000 СБ", "Клапан обратный", "Серия"));
		
		assemblyService.save(new AssemblyDTO("BPG301-00000 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG301-00000-01 СБ", "Блок питания", "Серия"));
		assemblyService.save(new AssemblyDTO("BPG301-00000-02 СБ", "Блок питания", "Серия"));
		
		
		
		partService.save(new PartDTO(0, "BPG000-00001", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00002", "Золотник", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00003", "Корпус клапана", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00004", "Клапан", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00005", "Пружина", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00006", "Заглушка", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00007", "Винт", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00008", "Штуцер", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00009", "Направляющая", "Серия"));
		partService.save(new PartDTO(0, "BPG000-00010 ГЧ", "Табличка", "Серия"));
		
		partService.save(new PartDTO(0, "BPG100-00001", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00002 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00002-01 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00002-02 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00002-03 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00002-04 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG100-00004", "Штуцер", "Серия"));
		
		partService.save(new PartDTO(0, "BPG201-10001", "Штуцер", "Серия"));
		
		partService.save(new PartDTO(0, "BPG301-00001", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "BPG301-00002 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG301-00002-01 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "BPG301-00002-02 ГЧ", "Табличка", "Серия"));
		
		partService.save(new PartDTO(0, "BPG302-00001", "Корпус", "Серия"));
		
		partService.save(new PartDTO(0, "DOC50-30009", "Пружина", "Серия"));
		
		
		
		standartService.save(new StandartDTO("O-Ring", "9,7x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("O-Ring", "10,6x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("O-Ring", "14,6x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("O-Ring", "16,6x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("O-Ring", "17,5x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("O-Ring", "20,5x1,9", "ISO 3601-1"));
		
		standartService.save(new StandartDTO("O-Ring", "17,5x2,5", "ISO 3601-1"));
		
		standartService.save(new StandartDTO("Шарик", "5 G60", "ГОСТ 3722-2014"));
		
		standartService.save(new StandartDTO("Штифт", "A3x10.60C2", "ГОСТ 14229-93"));
		
		vzkService.save(new VzkDTO("Заглушка металлическая", "ZMT.G14.00"));
		vzkService.save(new VzkDTO("Заглушка металлическая", "ZMT.G12.00"));
		vzkService.save(new VzkDTO("Заглушка металлическая", "ZMT.G34.00"));
		vzkService.save(new VzkDTO("Заглушка металлическая", "ZMT.M10.00"));
		
		vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.G14.00"));
		vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.G12.00"));
		vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.G34.00"));
		vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.M14.00"));
		vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.K24.00"));
		
		vzkService.save(new VzkDTO("Шайба резинометаллическая", "RMW.M14.00"));
		vzkService.save(new VzkDTO("Шайба резинометаллическая", "RMW.G12.00"));
		vzkService.save(new VzkDTO("Пневмогидроаккумулятор", "PGA.70.10"));
		
		
		buyService.save(new BuyDTO("EAAB.303635.001/005", "Колпак", "Технопривод"));
		buyService.save(new BuyDTO("EAAB.303635.001/009", "Палец", "Технопривод"));
		buyService.save(new BuyDTO("EAAB.303635.001/014", "Фланец", "Технопривод"));
		buyService.save(new BuyDTO("EAAB.303635.001/006", "Муфта", "Технопривод"));
		
		
		
	}
	
	//@PostConstruct
	private void initt() {
		//standartService.save(new StandartDTO("O-Ring", "23,5x1,9", "ISO 3601-1"));
		standartService.save(new StandartDTO("Винт", "M5x16-8.8-Zn", "ISO 4762"));
	}
	
	
	@PostConstruct
	private void init3() {
		
		/*
		partService.save(new PartDTO(0, "RGR100-10001", "Стакан", "Серия"));
		partService.save(new PartDTO(0, "RGR100-10003", "Толкатель", "Серия"));
		partService.save(new PartDTO(0, "RGR100-10004", "Пыльник", "Серия"));
		/*
		partService.save(new PartDTO(0, "RGR100-11001", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-01", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-02", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-03", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-04", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-06", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-07", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-08", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-09", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11001-11", "Корпус", "Серия"));
		
		partService.save(new PartDTO(0, "RGR100-11002", "Золотник", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11003", "Клапан обратный", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11004", "Пробка", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11005", "Пружина", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11006", "Стакан", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11007", "Шайба", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11007-01", "Шайба", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11008", "Насадка", "Серия"));
		partService.save(new PartDTO(0, "RGR100-11009", "Пружина", "Серия"));
		
		partService.save(new PartDTO(0, "RGR100-15003", "Пружина", "Серия"));
		partService.save(new PartDTO(0, "RGR100-15006", "Стакан", "Серия"));
		*/
		/*
		partService.save(new PartDTO(0, "RGG250/4-00001 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-01 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-02 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-03 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-04 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-05 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-06 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-07 ГЧ", "Табличка", "Серия"));
		partService.save(new PartDTO(0, "RGG250/4-00001-08 ГЧ", "Табличка", "Серия"));
		
		partService.save(new PartDTO(0, "RGG250/2-00001", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGG250/2-00001-01", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGG250/2-00001-02", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGG250/2-00001-03", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGG250/2-00001-04", "Шпилька", "Серия"));
		
		partService.save(new PartDTO(0, "RGG250-00003", "Заглушка", "Серия"));
		/*
		partService.save(new PartDTO(0, "RGG250-11101", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-01", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-02", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-03", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-04", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-05", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-06", "Корпус", "Серия"));
		partService.save(new PartDTO(0, "RGG250-11101-07", "Корпус", "Серия"));
		*/
		
		
		
		//partService.save(new PartDTO(0, "RGG250-11003", "Корпус", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11004", "Клапан", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11005", "Пружина", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11006", "Пружина", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11007", "Насадка", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11008", "Стакан", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11008-01", "Стакан", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11009", "Шайба", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-11009-01", "Шайба", "Серия"));
		
		
		//partService.save(new PartDTO(0, "RGG250-21001", "Золотник", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-21001-01", "Золотник", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-21001-02", "Золотник", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-21001-03", "Золотник", "Серия"));
		
		/*partService.save(new PartDTO(0, "RGG250-12001", "Заглушка", "Серия"));
		
		partService.save(new PartDTO(0, "RGG250-12002", "Кольцо", "Серия"));
		partService.save(new PartDTO(0, "RGG250-12002-01", "Кольцо", "Серия"));
		partService.save(new PartDTO(0, "RGG250-12002-02", "Кольцо", "Серия"));
		
		partService.save(new PartDTO(0, "RGG250-30001", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGG250-30001-01", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGG250-30001-02", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGG250-30001-03", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGG250-30001-04", "Крышка нагнетательная", "Серия"));
		
		partService.save(new PartDTO(0, "RGG250-31001", "Заглушка", "Серия"));
		partService.save(new PartDTO(0, "RGG250-31001-01", "Заглушка", "Серия"));
		partService.save(new PartDTO(0, "RGG250-31001-02", "Заглушка", "Серия"));
		*/
		
		
		//partService.save(new PartDTO(0, "RGG250-40001", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40001-01", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40001-02", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40001-03", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40002", "Дроссель", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40003", "Переходник", "Серия"));
		//partService.save(new PartDTO(0, "RGG250-40004", "Дроссель", "Серия"));
		
		//partService.save(new PartDTO(0, "RGG250-41001", "Пробка", "Серия"));
		
	}
	
	 
	@PostConstruct
	private void init4() {
		//partService.save(new PartDTO(0, "RGE100-80002", "Золотник", "Серия"));
		//partService.save(new PartDTO(0, "RGE100-70001", "Насадка", "Серия"));
		//partService.save(new PartDTO(0, "RGE100-70007", "Хвостовик", "Серия"));
		//partService.save(new PartDTO(0, "RGE100-70007-01", "Хвостовик", "Серия"));

		
		//partService.save(new PartDTO(0, "RGG100-00015", "Шайба межсекционная", "Серия"));

		/*
		partService.save(new PartDTO(0, "RGR100-00001", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-01", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-02", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-03", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-04", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-05", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-06", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-07", "Шпилька", "Серия"));
		partService.save(new PartDTO(0, "RGR100-00001-08", "Шпилька", "Серия"));
		*/
		
		//partService.save(new PartDTO(0, "RGR100-20001", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGR100-20001-01", "Крышка сливная", "Серия"));
		//partService.save(new PartDTO(0, "RGR100-20001-02", "Крышка сливная", "Серия"));
		
		//partService.save(new PartDTO(0, "RGR100-20002", "Банджо", "Серия"));
		//partService.save(new PartDTO(0, "RGR100-20003", "Болт", "Серия"));
		
		//partService.save(new PartDTO(0, "RGG100/3-00001 ГЧ", "Табличка", "Серия"));
		
		/*partService.save(new PartDTO(0, "RGR100-25001", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-01", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-02", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-03", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-04", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-05", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-06", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-07", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-08", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-09", "Крышка нагнетательная", "Серия"));
		partService.save(new PartDTO(0, "RGR100-25001-10", "Крышка нагнетательная", "Серия"));
		
		partService.save(new PartDTO(0, "RGR100-25001-15", "Крышка нагнетательная", "Задание"));
		
		partService.save(new PartDTO(0, "RGR100-25003", "Переходник", "Серия"));*/
		
		//partService.save(new PartDTO(0, "RGR100-28001", "Заглушка", "Серия"));
		//partService.save(new PartDTO(0, "RGR100-28001-01", "Заглушка", "Серия"));
	}
	
	
	@PostConstruct
	private void init5() {
		//standartService.save(new StandartDTO("O-Ring", "63,5x2,5", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "44x2,5", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "37x2,5", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "29,5x2,5", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "25,5x2,5", "ISO 3601-1"));
		///standartService.save(new StandartDTO("O-Ring", "23,5x2,5", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "18,5x2,5", "ISO 3601-1"));
		
		//standartService.save(new StandartDTO("O-Ring", "34x1,9", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "17,5x1,9", "ISO 3601-1"));
		//standartService.save(new StandartDTO("O-Ring", "18,5x1,9", "ISO 3601-1"));
		
		//standartService.save(new StandartDTO("Шайба", "B10-Zn", "DIN 127"));
		//standartService.save(new StandartDTO("Шайба", "B6-Zn", "DIN 127"));
		//standartService.save(new StandartDTO("Шайба", "12Т 65Г 019", "ГОСТ 6402-70"));
		
		//standartService.save(new StandartDTO("Гайка", "M10-10-Zn", "DIN 934"));
		//standartService.save(new StandartDTO("Гайка", "M12,5x1,5-10-Zn", "DIN 934"));
		
		//standartService.save(new StandartDTO("Винт", "M6x20-8.8-Zn", "ISO 4762"));
		
		//vzkService.save(new VzkDTO("Клапан антикавитационный", "KPV.100.10.000"));
		//vzkService.save(new VzkDTO("Клапан комбинированный", "KKD.100.10.000"));
		
		//vzkService.save(new VzkDTO("Клапан комбинированный", "KKD.250.00.000"));
		
		//vzkService.save(new VzkDTO("Клапан предохранительный", "KPD.250.10.200"));
		//vzkService.save(new VzkDTO("Клапан предохранительный", "KPD.250.00.200"));
		//vzkService.save(new VzkDTO("Клапан предохранительный", "KPD.250.00.000"));
		
		//vzkService.save(new VzkDTO("Заглушка пластиковая", "ZPT.G1.00"));
		//vzkService.save(new VzkDTO("Заглушка металлическая", "ZMT.G1.00"));
	}
}