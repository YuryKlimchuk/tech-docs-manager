package com.hydroyura.TechDocsManager.Data.DTO.Raw;

import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;

public class BlankRateDTO {
	
	private long id;
	private float rate;
	private BlankDTO blank;
	private RouteDTO route;
	
	
	
	public BlankRateDTO() {}

	public BlankRateDTO(long id, RouteDTO route, BlankDTO blank, float rate) {
		setBlank(blank);
		setRate(rate);
		setRoute(route);
		setId(id);
	}
	
	
	public float getRate() {
		return rate;
	}

	public BlankDTO getBlank() {
		return blank;
	}

	public RouteDTO getRoute() {
		return route;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setBlank(BlankDTO blank) {
		this.blank = blank;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	@Override
	public String toString() {
		return "id : " + getId() + ", rate : " + getRate();
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blank == null) ? 0 : blank.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(rate);
		result = prime * result + ((route == null) ? 0 : route.hashCode());
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
		BlankRateDTO other = (BlankRateDTO) obj;
		if (blank == null) {
			if (other.blank != null)
				return false;
		} else if (!blank.equals(other.blank))
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		return true;
	}
	
}
