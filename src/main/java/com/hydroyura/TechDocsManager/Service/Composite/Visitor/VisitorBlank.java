package com.hydroyura.TechDocsManager.Service.Composite.Visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;

@Component(value = "VisitorBlank")
public class VisitorBlank implements IVisitor {
	
	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	private Map<BlankDTO, Float> blankRates = new HashMap<>();
	

	@Override
	public void visit(ISpecificationRow row) {
		
		if(row instanceof SpecificationRowPart) {
			
			SpecificationRowPart part = (SpecificationRowPart) row;
			
			RouteDTO route = routeService.getById(part.getRoutes().get(0).getId()).get();
	
			BlankRateDTO blankRate = StreamSupport.stream(routeService.getBlankRates(route.getId()).spliterator(), false).collect(Collectors.toList()).get(0);
			
			if(!blankRates.containsKey(blankRate.getBlank())) {
				blankRates.put(blankRate.getBlank(), Float.valueOf(part.getCount() * blankRate.getRate()));
			} else {
				float value = blankRates.get(blankRate.getBlank());
				value = value + (part.getCount() * blankRate.getRate());
				blankRates.remove(blankRate.getBlank());
				blankRates.put(blankRate.getBlank(), value);
			}
			
			
		}

	}


	public Map<BlankDTO, Float> getBlankRates() {
		return blankRates;
	}
	
	
	

}
