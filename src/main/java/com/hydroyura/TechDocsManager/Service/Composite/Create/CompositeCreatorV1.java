package com.hydroyura.TechDocsManager.Service.Composite.Create;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowAssembly;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Route.Impl.RouteService;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;

@Component(value = "CompositeCreatorV1")
public class CompositeCreatorV1 implements ICompositeStructureCreator {
	
	
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
	
	
	
	@Autowired @Qualifier(value = "RouteService")
	private RouteService routeService;

	
	
	@Override
	public SpecificationRowAssembly createChildren(AssemblyDTO assemblyDTO, long count) {
		
		SpecificationRowAssembly rootAssembly = ICompositeStructureCreator.create(assemblyDTO, count);
		
		assemblyPartRateService.getByAssembly(assemblyDTO)
			.forEach(p -> {
							SpecificationRowPart partRow = ICompositeStructureCreator.create(p.getItem(), p.getCount());
							// Добавить маршруты
							partRow.setRoutes(
									StreamSupport.stream(routeService.getRoutesByPart(p.getItem()).spliterator(), false)
										.collect(Collectors.toList())
							);
							if(partRow.getRoutes() != null && partRow.getRoutes().size() == 1) 
								partRow.setSelectedRoute(partRow.getRoutes().get(0));
							// -----------------
							rootAssembly.add(partRow);
						  }
			);
		
		assemblyStandartRateService.getByAssembly(assemblyDTO)
			.forEach(p -> rootAssembly.add(ICompositeStructureCreator.create(p.getItem(), p.getCount())));
		
		assemblyVzkRateService.getByAssembly(assemblyDTO)
			.forEach(p -> rootAssembly.add(ICompositeStructureCreator.create(p.getItem(), p.getCount())));
		
		assemblyBuyRateService.getByAssembly(assemblyDTO)
			.forEach(p -> rootAssembly.add(ICompositeStructureCreator.create(p.getItem(), p.getCount())));
		
		assemblyAssemblyRateService.getByAssembly(assemblyDTO)
			.forEach(p -> rootAssembly.add(createChildren(p.getItem(), p.getCount())));
		
		
		
		return rootAssembly;
	}

}
