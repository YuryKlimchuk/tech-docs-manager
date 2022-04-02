package com.hydroyura.TechDocsManager.Service.PartRateCalculator;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;

public class RouteWrapper implements IRouteWrapper{

	private RouteDTO route;
	
	private BlankRateDTO selectedBlankRate;
	
	private Map<Long, BlankRateDTO> blankRateVariants;
	
	public RouteWrapper(RouteDTO route, Iterable<BlankRateDTO> blankRates) {

		if(route == null) throw new NullPointerException(">>route<< can`t be null");
		this.route = route;
		
		
		this.blankRateVariants = StreamSupport.stream(blankRates.spliterator(), false)
				.map(t -> new AbstractMap.SimpleEntry<Long, BlankRateDTO>(t.getId(), t))
				.collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
		
		if(blankRateVariants.size() == 1) this.selectedBlankRate = blankRateVariants.get(blankRateVariants.keySet().iterator().next());
		
	}
	
	
	@Override
	public RouteDTO getRoute() {
		return route;
	}


	@Override
	public Optional<BlankRateDTO> getSelectedBlankRate() {
		if(selectedBlankRate == null) return Optional.empty();
		return Optional.of(selectedBlankRate);
	}


	@Override
	public List<BlankRateDTO> getBlankRateVariants() {	
		return blankRateVariants.values().stream().collect(Collectors.toList());
	}


	@Override
	public void setBlankRate(long blankRateId) {
		selectedBlankRate = blankRateVariants.get(blankRateId);
	}

}
