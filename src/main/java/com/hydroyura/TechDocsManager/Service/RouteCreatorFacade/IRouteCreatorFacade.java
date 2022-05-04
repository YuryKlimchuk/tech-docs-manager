package com.hydroyura.TechDocsManager.Service.RouteCreatorFacade;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

public interface IRouteCreatorFacade {
	
	public Optional<PartDTO> getPart();
	public boolean setPart(PartDTO part);
	
	public String getNumber();
	public boolean setNumber(String number);
	
	public boolean addBlank(Optional<BlankDTO> blankRate, float rate);
	public void deleteBlank(Optional<BlankDTO> blankRate);
	public Map<BlankDTO, Float> getBlanks();
	
	public boolean addOperation(OperationDTO operation);
	public void deleteOperation(long id);
	public List<OperationDTO> getOperations();
	
	public void reset();
	
	
}
