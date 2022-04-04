package com.hydroyura.TechDocsManager.Service.Route;

import java.util.List;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Service.IBaseService;

public interface IRouteService extends IBaseService<RouteDTO, Long>{
	
	
	
	public RouteDTO save(RouteDTO dto, List<BlankRateDTO> blankRateDTOs, List<OperationDTO> operationDTOs);
	public Iterable<BlankRateDTO> getBlankRates(long id);
	public Iterable<OperationDTO> getOperations(long id);
	public Iterable<RouteDTO> getRoutesByPart(PartDTO partDTO);
	
	
}
