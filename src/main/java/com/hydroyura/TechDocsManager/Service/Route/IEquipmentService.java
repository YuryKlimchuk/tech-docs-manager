package com.hydroyura.TechDocsManager.Service.Route;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Route.EquipmentDTO;

public interface IEquipmentService {
	public Iterable<EquipmentDTO> getAll();
	public Optional<EquipmentDTO> getById(long id);
	public Optional<EquipmentDTO> save(EquipmentDTO dto);
}
