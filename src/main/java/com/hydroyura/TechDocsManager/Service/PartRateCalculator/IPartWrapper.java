package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.List;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

public interface IPartWrapper {
	
	
	public PartDTO getPart();
	public long getCount();
	public Optional<IRouteWrapper> getSelectedRoute();
	public Optional<BlankRateDTO> getSelectedBlankRate();

	public List<IRouteWrapper> getRouteVariants();
	public void setRoute(long routeId);
	
	public List<BlankRateDTO> getBlankRateVariants();
	
}
