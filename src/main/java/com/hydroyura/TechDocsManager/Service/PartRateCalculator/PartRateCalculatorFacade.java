package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.Map;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

public class PartRateCalculatorFacade implements IPartRateCalculatorFacade {

	private AssemblyDTO assembly;
	private Map<Long, PartWrapper> wrappedParts;
	
	
	public PartRateCalculatorFacade(AssemblyDTO assembly, Map<Long, PartWrapper> wrappedParts) {
		if(assembly == null) throw new NullPointerException(">>>assembly<<< can`t be null");
		if(wrappedParts == null) throw new NullPointerException(">>>wrappedParts<<< can`t be null");
		
		this.assembly = assembly;
		this.wrappedParts = wrappedParts;
	}

	@Override
	public AssemblyDTO getAssembly() {
		return assembly;
	}

	@Override
	public Iterable<PartWrapper> getWrappedParts() {
		return wrappedParts.values();
	}

	@Override
	public Optional<PartWrapper> getWrappedPartById(long id) {
		if(wrappedParts.containsKey(id)) return Optional.of(wrappedParts.get(id)); 
		return Optional.empty();
	}

	// Может нужно выкидывать ошибку если ID несуществует ???
	@Override
	public Optional<IRouteWrapper> getRoute(long id) {
		return wrappedParts.get(id).getSelectedRoute();
	}

	@Override
	public Optional<BlankRateDTO> getBlankRate(long id) {
		
		if(getRoute(id).isEmpty()) return Optional.empty();
		if(getRoute(id).get().getSelectedBlankRate().isEmpty()) return Optional.empty();
		
		return getRoute(id).get().getSelectedBlankRate();
	}

	@Override
	public long getPartsCount() {
		return wrappedParts.size();
	}

	@Override
	public long getPartsWithoutRouteCount() {
		return wrappedParts.entrySet().stream().filter(t -> t.getValue().getSelectedRoute().isEmpty()).count();
	}

	@Override
	public long getPartsWithoutBlankRateCount() {
		return wrappedParts.entrySet().stream().filter(t -> t.getValue().getSelectedBlankRate().isEmpty()).count();
	}




	
}
