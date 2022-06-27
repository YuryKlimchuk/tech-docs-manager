package com.hydroyura.TechDocsManager.Controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferSpecificationElementRestController {
	
	@Autowired @Qualifier(value = "PartService")
	private AbstractSpecificationElementService<PartDTO, PartEntity> partService;

	@Autowired @Qualifier(value = "VzkService")
	private AbstractSpecificationElementService<VzkDTO, VzkEntity> vzkService;
	
	@Autowired @Qualifier(value = "StandartService")
	private AbstractSpecificationElementService<StandartDTO, StandartEntity> standartService;
	
	@Autowired @Qualifier(value = "BuyService")
	private AbstractSpecificationElementService<BuyDTO, BuyEntity> buyService;

	@Autowired @Qualifier(value = "AssemblyService")
	private AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity> assemblyService;
	
	
	
	
	@Autowired @Qualifier(value = "AssemblyPartRateService")
	private AbstractAssemblyRateService<PartEntity, PartDTO> partRateService;
	
	@Autowired @Qualifier(value = "AssemblyAssemblyRateService")
	private AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> assemblyRateService;
	
	@Autowired @Qualifier(value = "AssemblyBuyRateService")
	private AbstractAssemblyRateService<BuyEntity, BuyDTO> buyRateService;
	
	@Autowired @Qualifier(value = "AssemblyVzkRateService")
	private AbstractAssemblyRateService<VzkEntity, VzkDTO> vzkRateService;
	
	@Autowired @Qualifier(value = "AssemblyStandartRateService")
	private AbstractAssemblyRateService<StandartEntity, StandartDTO> standartRateService;
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/parts")
	public ResponseEntity<?> getParts() {
		Iterable<PartDTO> partDTOs = partService.getAll();
		return new ResponseEntity<Iterable<PartDTO>>(partDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vzks")
	public ResponseEntity<?> getVzks() {
		Iterable<VzkDTO> vzkDTOs = vzkService.getAll();
		return new ResponseEntity<Iterable<VzkDTO>>(vzkDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/standarts")
	public ResponseEntity<?> gets() {
		Iterable<StandartDTO> vzkDTOs = standartService.getAll();
		return new ResponseEntity<Iterable<StandartDTO>>(vzkDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/buys")
	public ResponseEntity<?> getBuys() {
		Iterable<BuyDTO> vzkDTOs = buyService.getAll();
		return new ResponseEntity<Iterable<BuyDTO>>(vzkDTOs, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/assemblies")
	public ResponseEntity<?> getAssemblies() {
		Iterable<AssemblyDTO> assemblyDTOs = assemblyService.getAll();
		return new ResponseEntity<Iterable<AssemblyDTO>>(assemblyDTOs, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/part-rate")
	public ResponseEntity<?> getPartRates() {
		Iterable<BaseAssemblyRateDTO<PartDTO>> assemblyDTOs = partRateService.getAll();
		return new ResponseEntity<>(assemblyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/assembly-rate")
	public ResponseEntity<?> getAssemblyRates() {
		Iterable<BaseAssemblyRateDTO<AssemblyDTO>> assemblyDTOs = assemblyRateService.getAll();
		return new ResponseEntity<>(assemblyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/buy-rate")
	public ResponseEntity<?> getBuyRates() {
		Iterable<BaseAssemblyRateDTO<BuyDTO>> assemblyDTOs = buyRateService.getAll();
		return new ResponseEntity<>(assemblyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vzk-rate")
	public ResponseEntity<?> getVzkRates() {
		Iterable<BaseAssemblyRateDTO<VzkDTO>> assemblyDTOs = vzkRateService.getAll();
		return new ResponseEntity<>(assemblyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/standart-rate")
	public ResponseEntity<?> getStandartRates() {
		Iterable<BaseAssemblyRateDTO<StandartDTO>> assemblyDTOs = standartRateService.getAll();
		return new ResponseEntity<>(assemblyDTOs, HttpStatus.OK);
	}
	
}
