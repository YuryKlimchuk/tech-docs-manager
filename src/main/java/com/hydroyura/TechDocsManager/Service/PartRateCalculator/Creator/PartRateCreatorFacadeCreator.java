package com.hydroyura.TechDocsManager.Service.PartRateCalculator.Creator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.IPartRateCalculatorFacade;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.IRouteWrapper;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.PartRateCalculatorFacade;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.PartWrapper;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.RouteWrapper;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;

@Component(value = "PartRateCreatorFacadeCreator")
public class PartRateCreatorFacadeCreator implements IPartRateCreatorFacadeCreator {

	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	@Override
	public IPartRateCalculatorFacade create(AssemblyDTO assembly, Map<PartDTO, List<Long>> parts) {
		System.out.println("Создаем фасад для расчета норм");
		
		// key >> part ID
		Map<Long, PartWrapper> wrappedParts = parts.entrySet().stream()
				.map(p -> new PartWrapper(p.getKey(), p.getValue().stream().mapToLong(l -> l).sum(), getRoutes(p.getKey())))	
				.collect(Collectors.toMap(m -> m.getPart().getId(), m -> m));
		
		return new PartRateCalculatorFacade(assembly, wrappedParts);
	}
	
	
	private Iterable<IRouteWrapper> getRoutes(PartDTO part) {

		return 
				StreamSupport.stream(routeService.getRoutesByPart(part).spliterator(), false)
					.map(t -> new RouteWrapper(t, routeService.getBlankRates(t.getId())))
					.collect(Collectors.toList());
	}
	
	//private Iterable<IRouteWrapper> getRoutes(PartDTO part)

}
