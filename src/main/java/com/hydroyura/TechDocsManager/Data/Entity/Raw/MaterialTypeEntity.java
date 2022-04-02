package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.BaseAbstractEntity;

@Entity
@Table(name = "material_type")
public class MaterialTypeEntity extends BaseAbstractEntity {
	
	public MaterialTypeEntity() {}
	
	public MaterialTypeEntity(long id, String type) {
		setId(id);
		setNumber(type);
	}
	
	@OneToMany(mappedBy = "type")
	public Set<MaterialEntity> materials = new HashSet<>();

	public Set<MaterialEntity> getMaterials() {
		return materials;
	}

	public void setMaterials(Set<MaterialEntity> materials) {
		this.materials = materials;
	}
	
}
