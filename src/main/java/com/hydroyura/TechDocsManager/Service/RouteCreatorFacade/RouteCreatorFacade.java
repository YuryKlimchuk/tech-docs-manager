package com.hydroyura.TechDocsManager.Service.RouteCreatorFacade;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;

@Component(value = "RouteCreatorFacade")
public class RouteCreatorFacade implements IRouteCreatorFacade {
	
	public static final String DEFAULT_NUMBER = "Введите номер детали";
	
	private String number = DEFAULT_NUMBER;
	private PartDTO part;
	
	private Map<BlankDTO, Float> blankRates = new LinkedHashMap<>();
	private List<OperationDTO> operations = new LinkedList<>();
	
	
	@Override
	public Optional<PartDTO> getPart() {
		return (part == null) ? Optional.empty() : Optional.of(part);
	}

	@Override
	public boolean setPart(PartDTO part) {
		if(part == null || part.equals(this.part)) return false;
		this.part = part;
		return true;
	}
	
	
	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public boolean setNumber(String number) {
		if(number == null || number.equals(this.number)) return false;
		
		this.number = number;
		return true;
	}

	
	
	@Override
	public boolean addBlank(Optional<BlankDTO> blank, float rate) {
		if(blank.isEmpty()) return false;
		if(blankRates.containsKey(blank.get()) || rate <= 0) return false;
		
		blankRates.put(blank.get(), Float.valueOf(rate));
		return true;
	}

	@Override
	public Map<BlankDTO, Float> getBlanks() {
		return blankRates;
	}

	@Override
	public void deleteBlank(Optional<BlankDTO> blankRate) {
		if(blankRate.isPresent()) blankRates.remove(blankRate.get());
	}

	@Override
	public boolean addOperation(OperationDTO operation) {
		return operations.add(operation);
	}

	@Override
	public List<OperationDTO> getOperations() {
		return operations;
	}

	@Override
	public void deleteOperation(long id) {
		operations.remove((int) id);
	}

	@Override
	public void reset() {
		setNumber(DEFAULT_NUMBER);
		setPart(null);
		getBlanks().clear();
		getOperations().clear();
	}
}
