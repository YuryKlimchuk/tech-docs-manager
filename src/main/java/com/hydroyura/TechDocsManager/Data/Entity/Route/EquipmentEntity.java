package com.hydroyura.TechDocsManager.Data.Entity.Route;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.BaseAbstractEntity;

@Entity
@Table(name = "equipment")
public class EquipmentEntity extends BaseAbstractEntity {
	
	public EquipmentEntity() {}
	
	public EquipmentEntity(long id, String number) {
		setId(id);
		setNumber(number);
	}
	
	@OneToMany(mappedBy = "equipment")
	private List<OperationEntity> operations = new ArrayList<>();

	public List<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationEntity> operations) {
		this.operations = operations;
	}

	
	
}