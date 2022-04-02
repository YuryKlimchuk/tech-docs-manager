package com.hydroyura.TechDocsManager.Data.DTO.Raw;

public class BlankDTO {
	
	private long id;
	private MaterialDTO material;
	private SortamentDTO sortament;
	
	public BlankDTO() {}

	public BlankDTO(long id, MaterialDTO material, SortamentDTO sortament) {
		setId(id);
		setMaterial(material);
		setSortament(sortament);
	}

	public long getId() {
		return id;
	}

	public MaterialDTO getMaterial() {
		return material;
	}

	public SortamentDTO getSortament() {
		return sortament;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMaterial(MaterialDTO material) {
		this.material = material;
	}

	public void setSortament(SortamentDTO sortament) {
		this.sortament = sortament;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((sortament == null) ? 0 : sortament.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlankDTO other = (BlankDTO) obj;
		if (id != other.id)
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (sortament == null) {
			if (other.sortament != null)
				return false;
		} else if (!sortament.equals(other.sortament))
			return false;
		return true;
	}
}
