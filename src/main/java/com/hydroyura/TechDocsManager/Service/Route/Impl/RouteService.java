package com.hydroyura.TechDocsManager.Service.Route.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.OperationEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.Raw.BlankRateRepository2;
import com.hydroyura.TechDocsManager.Data.Repository.Route.OperationRepository2;
import com.hydroyura.TechDocsManager.Data.Repository.Route.RouteRepository;
import com.hydroyura.TechDocsManager.Service.Route.IRouteService;

@Service(value = "RouteService")
public class RouteService implements IRouteService {

	@Autowired @Qualifier(value = "RouteRepository")
	private RouteRepository repository;
	
	@Autowired @Qualifier(value = "BlankRateRepository")
	private JpaRepository<BlankRateEntity, Long> blankRateRepository;
	
	@Autowired @Qualifier(value = "BlankRateRepository2")
	private BlankRateRepository2 blankRateRepository2;
	
	@Autowired @Qualifier(value = "OperationRepository")
	private JpaRepository<OperationEntity, Long> operationRepository;
	
	@Autowired @Qualifier(value = "OperationRepository2")
	private OperationRepository2 operationRepository2;
	
	
	
	@Autowired @Qualifier(value = "BlankRateConverter")
	private IConverter<BlankRateEntity, BlankRateDTO> blankRateConverter;
	
	@Autowired @Qualifier(value = "RouteConverter")
	private IConverter<RouteEntity, RouteDTO> converter;
	
	@Autowired @Qualifier(value = "BlankConverter")
	private IConverter<BlankEntity, BlankDTO> blankConverter;
	
	@Autowired @Qualifier(value = "PartConverter")
	private IConverter<PartEntity, PartDTO> partConverter;
	
	@Autowired @Qualifier(value = "OperationConverter")
	private IConverter<OperationEntity, OperationDTO> operationConverter;
	
	
	
	@Override
	public RouteDTO save(RouteDTO dto) {
		
		try {
			return converter.convertFromEntityToDto(repository.save(converter.convertFromDtoToEntity(dto)));
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@Transactional
	public RouteDTO save(RouteDTO dto, List<BlankRateDTO> blankRateDTOs, List<OperationDTO> operationDTOs) {
		
		try {
			RouteEntity route = repository.save(converter.convertFromDtoToEntity(dto));
			
			//System.out.println("ROUTE ID = " + route.getId());
			
			blankRateConverter.convertListFromDtoToEntity(blankRateDTOs).stream()
				.map(p -> {p.setRoute(route); return p;})
				.forEach(p -> blankRateRepository.save(p));
			
			operationConverter.convertListFromDtoToEntity(operationDTOs).stream()
				.map(p -> {p.setRoute(route); return p;})
				.forEach(p -> operationRepository.save(p));
			
			return converter.convertFromEntityToDto(repository.save(route));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Iterable<RouteDTO> getAll() {
		
		try {
			return converter.convertListFromEntityToDto(repository.findAll());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return new HashSet<>();
	}

	@Override
	public Optional<RouteDTO> getById(long id) {
		
		Optional<RouteEntity> entity = repository.findById(id);
		
		if(entity.isPresent()) return Optional.of(converter.convertFromEntityToDto(entity.get()));
		
		return Optional.empty();
	}

	@Override
	public Iterable<BlankRateDTO> getBlankRates(long id) {
		/*
		Optional<RouteEntity> route = repository.findById(id);
		
		if(route.isPresent() && !route.get().getRates().isEmpty()) {
			route.get().getRates().forEach(System.out::println);     /// ???????
			return blankRateConverter.convertListFromEntityToDto(route.get().getRates());
		}

		return new ArrayList<>();
		*/
		return blankRateConverter.convertListFromEntityToDto(blankRateRepository2.findByRoute_Id(id));
	}

	@Override
	public Iterable<OperationDTO> getOperations(long id) {
		/*
		Optional<RouteEntity> route = repository.findById(id);
		
		if(route.isPresent() && !route.get().getOperations().isEmpty()) {
			route.get().getRates().forEach(System.out::println);     /// ???????
			return operationConverter.convertListFromEntityToDto(route.get().getOperations());
		}
		
		return new ArrayList<>();
		*/
		return operationConverter.convertListFromEntityToDto(operationRepository2.findByRoute_Id(id));
	}

	@Override
	public Iterable<RouteDTO> getRoutesByPart(PartDTO partDTO) {
		
		try {
			return StreamSupport
					.stream(repository.findByPart(partConverter.convertFromDtoToEntity(partDTO)).spliterator(), false)
					.map(converter::convertFromEntityToDto)
					.collect(Collectors.toList());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}


}
