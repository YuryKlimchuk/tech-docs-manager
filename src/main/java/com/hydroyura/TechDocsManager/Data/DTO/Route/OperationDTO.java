package com.hydroyura.TechDocsManager.Data.DTO.Route;
/*
 * TO DO
 * - добавить RouteDTO
 */
public class OperationDTO {

	private long id;
	private float time;
	private int index;
	private EquipmentDTO equipment;
	private OperationTypeDTO type;
	
	
	public OperationDTO() {}
	
	public OperationDTO(long id, float time, int index, EquipmentDTO equipment, OperationTypeDTO type) {
		setId(id);
		setTime(time);
		setIndex(index);
		setEquipment(equipment);
		setType(type);
	}


	public long getId() {
		return id;
	}
	public float getTime() {
		return time;
	}
	public int getIndex() {
		return index;
	}
	public EquipmentDTO getEquipment() {
		return equipment;
	}
	public OperationTypeDTO getType() {
		return type;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setEquipment(EquipmentDTO equipment) {
		this.equipment = equipment;
	}
	public void setType(OperationTypeDTO type) {
		this.type = type;
	}
	
	
	
	
}
