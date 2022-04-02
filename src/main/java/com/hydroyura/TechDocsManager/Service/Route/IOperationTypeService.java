package com.hydroyura.TechDocsManager.Service.Route;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Route.OperationTypeDTO;

public interface IOperationTypeService {
	public Iterable<OperationTypeDTO> getAll();
	public Optional<OperationTypeDTO> getById(long id);
	public Optional<OperationTypeDTO> save(OperationTypeDTO dto);
}
