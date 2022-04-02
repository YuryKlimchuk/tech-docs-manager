package com.hydroyura.TechDocsManager.Service.Route;

import java.util.List;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

public interface IRouteService {
	
	public RouteDTO save(RouteDTO dto);
	public RouteDTO save(RouteDTO dto, List<BlankRateDTO> blankRateDTOs, List<OperationDTO> operationDTOs);
	
	public Optional<RouteDTO> getById(long id);
	
	public Iterable<BlankRateDTO> getBlankRates(long id);
	public Iterable<RouteDTO> getAll();
	public Iterable<OperationDTO> getOperations(long id);
	
	public Iterable<RouteDTO> getRoutesByPart(PartDTO partDTO);
}
