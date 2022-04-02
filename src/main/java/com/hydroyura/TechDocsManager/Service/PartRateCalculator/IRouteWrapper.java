package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.List;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;

public interface IRouteWrapper {
	
	public RouteDTO getRoute();

	public Optional<BlankRateDTO> getSelectedBlankRate();
	
	public List<BlankRateDTO> getBlankRateVariants();
	public void setBlankRate(long blankRateId);
}
