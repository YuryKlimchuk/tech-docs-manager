package com.hydroyura.TechDocsManager.Service.Composite.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;

@Component(value = "VisitorOperation")
public class VisitorOperation implements IVisitor {

	@Autowired @Qualifier(value = "RouteService")
	private IRouteService routeService;
	
	private Map<EquipmentDTO, Float> operationTime = new HashMap<>();
	
	@Override
	public void visit(ISpecificationRow row) {
		
		if(row instanceof SpecificationRowPart) {
			
			SpecificationRowPart part = (SpecificationRowPart) row;
			
			System.out.println("ROUTE ID = " + part.getRoutes().get(0).getId());
			
			List<OperationDTO> operations = StreamSupport.stream(routeService.getOperations(part.getRoutes().get(0).getId()).spliterator(), false).collect(Collectors.toList());
			System.out.println("OPERATIONS COUNT = " + operations.size());
			
			
			operations.forEach(new Consumer<OperationDTO>() {
				@Override
				public void accept(OperationDTO op) {
					
					EquipmentDTO eq = op.getEquipment();
					
					if(!operationTime.containsKey(eq)) {
						operationTime.put(eq, op.getTime() * part.getCount());
					} else {
						float time = operationTime.get(eq);
						operationTime.remove(eq);
						operationTime.put(eq, time + op.getTime() * part.getCount());
					}
					
				}
			});
			
			
		}

	}

	public Map<EquipmentDTO, Float> getOperationTime() {
		return operationTime;
	}
	
	
	

}
