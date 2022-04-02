package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.BaseAbstractEntity;

@Entity
@Table(name = "sortament_type")
public class SortamentTypeEntity extends BaseAbstractEntity{
	
	public SortamentTypeEntity() {}
	
	public SortamentTypeEntity(long id, String type) {
		setId(id);
		setNumber(type);
	}
	
	@OneToMany(mappedBy = "type")
	public Set<SortamentEntity> sortaments = new HashSet<>();

	public Set<SortamentEntity> getMaterials() {
		return sortaments;
	}

	public void setMaterials(Set<SortamentEntity> sortaments) {
		this.sortaments = sortaments;
	}
	
}
