package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

public interface IPartRateCalculatorFacade {
	
	public AssemblyDTO getAssembly();
	public Iterable<PartWrapper> getWrappedParts();
	public Optional<PartWrapper> getWrappedPartById(long id);
	
	
	public Optional<IRouteWrapper> getRoute(long id);
	
	public Optional<BlankRateDTO> getBlankRate(long id);
	
	public long getPartsCount();
	public long getPartsWithoutRouteCount();
	public long getPartsWithoutBlankRateCount();
	
}
