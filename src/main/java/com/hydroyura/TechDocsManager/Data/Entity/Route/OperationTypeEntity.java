package com.hydroyura.TechDocsManager.Data.Entity.Route;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.BaseAbstractEntity;

@Entity
@Table(name = "operation_type")
public class OperationTypeEntity extends BaseAbstractEntity {
	
	
	@OneToMany(mappedBy = "type")
	private Set<OperationEntity> operations = new HashSet<>();
	
	public OperationTypeEntity() {}
	
	public OperationTypeEntity(long id, String number) {
		setId(id);
		setNumber(number);
	}

	public Set<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(Set<OperationEntity> operations) {
		this.operations = operations;
	}
	
}
