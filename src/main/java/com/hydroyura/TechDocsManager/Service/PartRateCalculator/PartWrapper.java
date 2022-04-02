package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

public class PartWrapper implements IPartWrapper	{

	private PartDTO part;
	private long count;
	private IRouteWrapper selectedRoute;
	
	// Key - ID
	private Map<Long, IRouteWrapper> routeVariants;
	

	public PartWrapper(PartDTO part, long count, Iterable<IRouteWrapper> routes) {

		if(part == null) throw new NullPointerException(">>part<< can`t be null");
		if(routes == null) throw new NullPointerException(">>routes<< can`t be null");
		
		this.part = part;
		this.count = count;
		
		this.routeVariants = StreamSupport.stream(routes.spliterator(), false)
				.map(t -> new AbstractMap.SimpleEntry<Long, IRouteWrapper>(t.getRoute().getId(), t))
				.collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
		
		if(routeVariants.size() == 1) this.selectedRoute = routeVariants.get(routeVariants.keySet().iterator().next());

	}
	
	

	@Override
	public PartDTO getPart() {
		return part;
	}

	
	
	@Override
	public long getCount() {
		return count;
	}

	@Override
	public Optional<IRouteWrapper> getSelectedRoute() {
		if(selectedRoute == null) return Optional.empty();
		return Optional.of(selectedRoute);
	}

	@Override
	public List<IRouteWrapper> getRouteVariants() {
		return routeVariants.values().stream().collect(Collectors.toList());
	}

	


	@Override
	public void setRoute(long routeId) {
		selectedRoute = routeVariants.get(routeId);
	}



	@Override
	public List<BlankRateDTO> getBlankRateVariants() {
		
		if(getSelectedRoute().isEmpty()) return new ArrayList<>();
		
		return getSelectedRoute().get().getBlankRateVariants();
	}



	@Override
	public Optional<BlankRateDTO> getSelectedBlankRate() {
		if(selectedRoute == null) return Optional.empty();
		return selectedRoute.getSelectedBlankRate();
	}
	
	
	
	
}
